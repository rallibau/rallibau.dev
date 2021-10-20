package com.rallibau.shared.domain.events.acl;

import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class UserCreatedDomainEvent extends DomainEvent {

    private final String userName;

    public UserCreatedDomainEvent() {
        this.userName = null;
    }

    public UserCreatedDomainEvent(String aggregateId, String userName) {
        super(aggregateId);
        this.userName = userName;
    }

    @Override
    public String eventName() {
        return "user.created";
    }

    public String userName() {
        return userName;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("userName", userName);
        }};
    }

    @Override
    public UserCreatedDomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new UserCreatedDomainEvent(aggregateId,
                (String) body.get("userName"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserCreatedDomainEvent that = (UserCreatedDomainEvent) o;
        return aggregateId().equals(that.aggregateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId());
    }
}
