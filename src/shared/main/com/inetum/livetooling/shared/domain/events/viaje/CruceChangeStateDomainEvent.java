package com.inetum.livetooling.shared.domain.events.viaje;

import com.inetum.livetooling.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class CruceChangeStateDomainEvent extends DomainEvent {

    public CruceChangeStateDomainEvent() {
        super(null);
    }

    public CruceChangeStateDomainEvent(String aggregateId) {
        super(aggregateId);
    }


    @Override
    public String eventName() {
        return "cruce.change_state";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {
          };
    }

    @Override
    public CruceChangeStateDomainEvent fromPrimitives(String aggregateId,
                                                      HashMap<String, Serializable> body,
                                                      String eventId,
                                                      String occurredOn) {
        return new CruceChangeStateDomainEvent(aggregateId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CruceChangeStateDomainEvent that = (CruceChangeStateDomainEvent) o;
        return aggregateId().equals(that.aggregateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId());
    }
}
