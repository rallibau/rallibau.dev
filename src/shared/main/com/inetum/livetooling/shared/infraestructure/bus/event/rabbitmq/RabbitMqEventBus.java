package com.inetum.livetooling.shared.infraestructure.bus.event.rabbitmq;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.event.DomainEvent;
import com.inetum.livetooling.shared.domain.bus.event.EventBus;
import org.springframework.amqp.AmqpException;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Primary
@Service
public class RabbitMqEventBus implements EventBus {
    private final RabbitMqPublisher publisher;
    private final String exchangeName;

    public RabbitMqEventBus(RabbitMqPublisher publisher) {
        this.publisher = publisher;
        this.exchangeName = "domain_events";
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    @Override
    public void publish(DomainEvent domainEvent) {
        try {
            this.publisher.publish(domainEvent, exchangeName);
        } catch (AmqpException error) {
            //TODO: cola de errores
            error.printStackTrace();
        }
    }
}
