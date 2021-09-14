package com.rallibau.shared.domain.events.bpm;

import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class ProcessCreatedDomainEvent extends DomainEvent {

    private final String processName;

    public ProcessCreatedDomainEvent() {
        this.processName = null;
    }

    public ProcessCreatedDomainEvent(String aggregateId, String processName) {
        super(aggregateId);
        this.processName = processName;
    }

    @Override
    public String eventName() {
        return "process.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("name", processName);
        }};
    }

    @Override
    public ProcessCreatedDomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new ProcessCreatedDomainEvent(aggregateId,
                (String) body.get("name"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ProcessCreatedDomainEvent that = (ProcessCreatedDomainEvent) o;
        return aggregateId().equals(that.aggregateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId());
    }
}
