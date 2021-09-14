package com.rallibau.shared.infraestructure.bus.event.spring;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEvent;
import com.rallibau.shared.domain.bus.event.EventBus;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;

import java.util.List;

//@Primary
//@Service
public class SpringApplicationEventBus implements EventBus {

    private final ApplicationEventPublisher publisher;

    public SpringApplicationEventBus(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    @Override
    public void publish(final DomainEvent event) {
        this.publisher.publishEvent(event);
    }
}
