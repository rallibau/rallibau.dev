package com.rallibau.shared.infraestructure.bus.command.rabbitmq;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.infraestructure.bus.command.CommandHandlersInformation;
import com.rallibau.shared.infraestructure.bus.event.DomainEventJsonDeserializer;
import com.rallibau.shared.infraestructure.bus.event.DomainEventSubscribersInformation;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqPublisher;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;

@Service
public class RabbitMqCommandsConsumer {
    private final String CONSUMER_NAME = "command_events_consumer";
    private final int MAX_RETRIES = 2;
    private final DomainEventJsonDeserializer deserializer;
    private final ApplicationContext context;
    private final RabbitMqPublisher publisher;
    private final HashMap<String, Object> domainEventSubscribers = new HashMap<>();
    RabbitListenerEndpointRegistry registry;
    private CommandHandlersInformation information;

    public RabbitMqCommandsConsumer(
            RabbitListenerEndpointRegistry registry,
            CommandHandlersInformation information,
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
        String queue = message.getMessageProperties().getConsumerQueue();
        System.out.println(serializedMessage);

    }


}
