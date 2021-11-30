package com.rallibau.shared.infraestructure.bus.event.rabbitmq;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.Utils;
import com.rallibau.shared.domain.bus.event.DomainEvent;
import com.rallibau.shared.infraestructure.bus.event.DomainEventJsonDeserializer;
import com.rallibau.shared.infraestructure.bus.event.DomainEventSubscribersInformation;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqExchangeNameFormatter;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqPublisher;
import org.jetbrains.annotations.NotNull;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Service
public class RabbitMqDomainEventsConsumer {
    private final String CONSUMER_NAME = "domain_events_consumer";
    private final DomainEventJsonDeserializer deserializer;
    private final ApplicationContext context;
    private final RabbitMqPublisher publisher;
    private final HashMap<String, Object> domainEventSubscribers = new HashMap<>();
    RabbitListenerEndpointRegistry registry;
    private DomainEventSubscribersInformation information;

    public RabbitMqDomainEventsConsumer(
            RabbitListenerEndpointRegistry registry,
            DomainEventSubscribersInformation information,
            DomainEventJsonDeserializer deserializer,
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

        container.addQueueNames(information.rabbitMqFormattedNames());

        container.start();
    }

    @RabbitListener(id = CONSUMER_NAME, autoStartup = "true")
    public void consumer(Message message) throws Exception {
        String serializedMessage = new String(message.getBody());
        DomainEvent domainEvent = deserializer.deserialize(serializedMessage);

        String queue = message.getMessageProperties().getConsumerQueue();

        Object subscriber = domainEventSubscribers.containsKey(queue)
                ? domainEventSubscribers.get(queue)
                : subscriberFor(queue);
        try {
            getOnMethod(domainEvent, subscriber,queue).invoke(subscriber,domainEvent);
        } catch (Exception error) {
            handleConsumptionError(message, queue);
        }
    }

    @NotNull
    private Method getOnMethod(DomainEvent domainEvent, Object subscriber, String queue) throws Exception {
        try {
            return subscriber.getClass().getMethod("on", domainEvent.getClass());
        } catch (NoSuchMethodException e) {
            throw new Exception(String.format(
                    "The subscriber <%s> should implement a method `on` listening the domain event <%s>",
                    queue,
                    domainEvent.eventName()
            ));
        }
    }

    public void withSubscribersInformation(DomainEventSubscribersInformation information) {
        this.information = information;
    }

    private void handleConsumptionError(Message message, String queue) {
        if (hasBeenRedeliveredTooMuch(message)) {
            sendToDeadLetter(message, queue);
        } else {
            sendToRetry(message, queue);
        }
    }

    private void sendToRetry(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.retry("domain_events"), message, queue);
    }

    private void sendToDeadLetter(Message message, String queue) {
        sendMessageTo(RabbitMqExchangeNameFormatter.deadLetter("domain_events"), message, queue);
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

    private Object subscriberFor(String queue) throws Exception {
        String[] queueParts = queue.split("\\.");
        String subscriberName = Utils.toCamelFirstLower(queueParts[queueParts.length - 1]);

        try {
            Object subscriber = context.getBean(subscriberName);
            domainEventSubscribers.put(queue, subscriber);

            return subscriber;
        } catch (Exception e) {
            throw new Exception(String.format("There are not registered subscribers for <%s> queue", queue));
        }
    }
}
