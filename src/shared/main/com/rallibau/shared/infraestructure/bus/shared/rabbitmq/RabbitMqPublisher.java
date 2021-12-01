package com.rallibau.shared.infraestructure.bus.shared.rabbitmq;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.Command;
import com.rallibau.shared.domain.bus.event.DomainEvent;
import com.rallibau.shared.infraestructure.bus.command.CommandJsonSerializer;
import com.rallibau.shared.infraestructure.bus.event.DomainEventJsonSerializer;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class RabbitMqPublisher {
    public static final String STORE_EVENTS = "store_events";
    private final RabbitTemplate rabbitTemplate;

    public RabbitMqPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publish(DomainEvent domainEvent, String exchangeName) throws AmqpException {
        String serializedDomainEvent = DomainEventJsonSerializer.serialize(domainEvent);

        Message message = new Message(
                serializedDomainEvent.getBytes(),
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .build()
        );

        rabbitTemplate.send(exchangeName, domainEvent.eventName(), message);
        sendToStore(message);
    }

    public void publish(Command command, String exchangeName) throws AmqpException {
        String serializedCommandEvent = CommandJsonSerializer.serialize(command);
        Message message = new Message(
                serializedCommandEvent.getBytes(),
                MessagePropertiesBuilder.newInstance()
                        .setContentEncoding("utf-8")
                        .setContentType("application/json")
                        .build()
        );

        rabbitTemplate.send(exchangeName, command.formatQueueName(), message);
        sendToStore(message);
    }

    public void rePublish(Message message, String exchangeName, String routingKey) throws AmqpException {
        rabbitTemplate.send(exchangeName, routingKey, message);
    }

    private void sendToStore(Message message){
        rabbitTemplate.send(STORE_EVENTS, RabbitMqConfiguration.EVENT_STORE_QUEUE_NAME, message);
    }
}
