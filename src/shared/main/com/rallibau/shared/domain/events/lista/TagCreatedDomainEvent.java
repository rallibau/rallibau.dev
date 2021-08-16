package com.rallibau.shared.domain.events.lista;

import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class TagCreatedDomainEvent extends DomainEvent {
    private final String numero;
    private final String clienteId;

    public TagCreatedDomainEvent() {
        super(null);
        this.clienteId = null;
        this.numero = null;
    }

    public TagCreatedDomainEvent(String aggregateId, String numero, String clienteId) {
        super(aggregateId);
        this.numero = numero;
        this.clienteId = clienteId;
    }

    public String numero() {
        return numero;
    }

    public String clienteId() {
        return clienteId;
    }

    @Override
    public String eventName() {
        return "tag.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("numero", numero);
            put("clienteId", clienteId);
        }};
    }

    @Override
    public TagCreatedDomainEvent fromPrimitives(String aggregateId,
                                                HashMap<String, Serializable> body,
                                                String eventId,
                                                String occurredOn) {
        return new TagCreatedDomainEvent(aggregateId,
                (String) body.get("numero"),
                (String) body.get("clienteId"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagCreatedDomainEvent that = (TagCreatedDomainEvent) o;
        return aggregateId().equals(that.aggregateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId());
    }
}
