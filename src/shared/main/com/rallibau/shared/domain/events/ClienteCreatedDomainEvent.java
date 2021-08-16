package com.rallibau.shared.domain.events;

import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class ClienteCreatedDomainEvent extends DomainEvent {
    private final String name;

    public ClienteCreatedDomainEvent() {
        super(null);
        this.name = null;
    }

    public ClienteCreatedDomainEvent(String aggregateId, String name) {
        super(aggregateId);
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String eventName() {
        return "cliente.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return null;
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ClienteCreatedDomainEvent that = (ClienteCreatedDomainEvent) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
