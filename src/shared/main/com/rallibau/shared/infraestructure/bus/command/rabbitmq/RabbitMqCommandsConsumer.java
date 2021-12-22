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
    private final HashMap<String, Optional<Object>> commandsSubscribers = new HashMap<>();
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

    public void consume() throws CommandNotRegisteredError {
        AbstractMessageListenerContainer container = (AbstractMessageListenerContainer) registry.getListenerContainer(
                CONSUMER_NAME
        );

        container.addQueueNames(information.rabbitMqFormattedNames());

        container.start();
    }

    @RabbitListener(id = CONSUMER_NAME, autoStartup = "true")
    public void consumer(Message message) throws Exception {
        String serializedMessage = new String(message.getBody());
        String queue = message.getMessageProperties().getConsumerQueue();

        Optional<Object> subscriberOptional = commandsSubscribers.containsKey(queue)
                ? commandsSubscribers.get(queue)
                : subscriberFor(queue);

        if (subscriberOptional.isEmpty()) {
            throw new Exception(String.format("There are not registered subscribers for <%s> queue", queue));
        }
        Optional<Command> command = Optional.empty();
        try {
            Object subscriber = subscriberOptional.get();
            command = deserializer.deserialize(serializedMessage);
            if (command.isEmpty()) {
                throw new Exception("Message can not be empty");
            }
            Method commandHandleMethod = subscriber.getClass().getMethod("handle", command.get().getClass());
            commandHandleMethod.invoke(subscriber, command.get());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException error) {
            throw new Exception(String.format(
                    "The subscriber <%s> should implement a method `handle` to manage command <%s>",
                    queue,
                    command.isPresent() ? command.get().className() : "UNDEFINED"
            ));

        } catch (Exception error) {
            handleConsumptionError(message, queue);
        }

    }

    private Optional<Object> subscriberFor(String queue) {
        String[] queueParts = queue.split("\\.");
        String commandHandlerName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]).concat("Handler");

        if (context.containsBean(commandHandlerName)) {
            Optional<Object> commandHandler = Optional.of(context.getBean(commandHandlerName));
            commandsSubscribers.put(queue, commandHandler);
            return commandHandler;
        }
        return Optional.empty();

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

        publisher.rePublish(message, exchange, queue);
    }

    private boolean hasBeenRedeliveredTooMuch(Message message) {
        int MAX_RETRIES = 2;
        return (int) message.getMessageProperties().getHeaders().getOrDefault("redelivery_count", 0) >= MAX_RETRIES;
    }


}
