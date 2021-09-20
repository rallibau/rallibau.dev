package com.rallibau.shared.infraestructure.bus.command.rabbitmq;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.command.CommandNotRegisteredError;
import com.rallibau.shared.infraestructure.bus.command.CommandHandlersInformation;
import com.rallibau.shared.infraestructure.bus.command.CommandJsonDeserializer;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqExchangeNameFormatter;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqPublisher;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RabbitMqCommandsConsumer {
    private final String CONSUMER_NAME = "command_events_consumer";
    private final CommandJsonDeserializer deserializer;
    private final ApplicationContext context;
    private final RabbitMqPublisher publisher;
    private final HashMap<String, Object> commandsSubscribers = new HashMap<>();
    RabbitListenerEndpointRegistry registry;
    private final CommandHandlersInformation information;

    public RabbitMqCommandsConsumer(
            RabbitListenerEndpointRegistry registry,
            CommandHandlersInformation information,
            CommandJsonDeserializer deserializer,
            ApplicationContext context,
            RabbitMqPublisher publisher
    ) {
        this.registry = registry;
        this.information = information;
        this.deserializer = deserializer;
        this.context = context;
        this.publisher = publisher;
    }

    public void consume() {
        AbstractMessageListenerContainer container = (AbstractMessageListenerContainer) registry.getListenerContainer(
                CONSUMER_NAME
        );

        try {
            container.addQueueNames(information.rabbitMqFormattedNames());
        } catch (CommandNotRegisteredError commandNotRegisteredError) {
            commandNotRegisteredError.printStackTrace();
        }

        container.start();
    }

    @RabbitListener(id = CONSUMER_NAME, autoStartup = "true")
    public void consumer(Message message) throws Exception {
        String serializedMessage = new String(message.getBody());
        String queue = message.getMessageProperties().getConsumerQueue();

        Object subscriber = commandsSubscribers.containsKey(queue)
                ? commandsSubscribers.get(queue)
                : subscriberFor(queue);

        Optional<Command> command = deserializer.deserialize(serializedMessage);
        if (!command.isPresent()) {
            throw new Exception(String.format(
                    "The subscriber <%s> can't deserialize the message",
                    queue));
        }
        try {

            Method commandHandleMethod = subscriber.getClass().getMethod("handle", command.get().getClass());
            System.out.println(commandHandleMethod.getName());
            commandHandleMethod.invoke(subscriber, command.get());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException error) {
            throw new Exception(String.format(
                    "The subscriber <%s> should implement a method `handle` to manage command <%s>",
                    queue,
                    command.get().className()
            ));


        } catch (Exception error) {
            handleConsumptionError(message, queue);
        }

    }

    private Object subscriberFor(String queue) throws Exception {
        String[] queueParts = queue.split("\\.");
        String commandHandlerName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]).concat("Handler");

        try {
            Object commandHandler = context.getBean(commandHandlerName);
            commandsSubscribers.put(queue, commandHandler);

            return commandHandler;
        } catch (Exception e) {
            throw new Exception(String.format("There are not registered subscribers for <%s> queue", queue));
        }
    }

    private void handleConsumptionError(Message message, String queue) {
        if (hasBeenRedeliveredTooMuch(message)) {
            sendToDeadLetter(message, queue);
        } else {
            sendToRetry(message, queue);
        }
    }

    private void sendToRetry(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.retry("command_events"), message, queue);
    }

    private void sendToDeadLetter(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.deadLetter("command_events"), message, queue);
    }

    private void sendMessageTo(String exchange, Message message, String queue) {
        Map<String, Object> headers = message.getMessageProperties().getHeaders();

        headers.put("redelivery_count", (int) headers.getOrDefault("redelivery_count", 0) + 1);

        MessageBuilder.fromMessage(message).andProperties(
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .copyHeaders(headers)
                        .build());

        publisher.publish(message, exchange, queue);
    }

    private boolean hasBeenRedeliveredTooMuch(Message message) {
        int MAX_RETRIES = 2;
        return (int) message.getMessageProperties().getHeaders().getOrDefault("redelivery_count", 0) >= MAX_RETRIES;
    }


}
