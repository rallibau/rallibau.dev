package com.rallibau.shared.domain.events.viaje;

import com.rallibau.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class CruceCreatedDomainEvent extends DomainEvent {
    private final String ruta;
    private final String estado;

    public CruceCreatedDomainEvent() {
        super(null);
        this.ruta = null;
        this.estado = null;
    }

    public CruceCreatedDomainEvent(String aggregateId, String ruta, String estado) {
        super(aggregateId);
        this.ruta = ruta;
        this.estado = estado;
    }

    public String ruta() {
        return ruta;
    }

    public String estado() {
        return estado;
    }

    @Override
    public String eventName() {
        return "cruce.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("ruta", ruta);
            put("estado", estado);
        }};
    }

    @Override
    public CruceCreatedDomainEvent fromPrimitives(String aggregateId,
                                                  HashMap<String, Serializable> body,
                                                  String eventId,
                                                  String occurredOn) {
        return new CruceCreatedDomainEvent(aggregateId,
                (String) body.get("ruta"),
                (String) body.get("estado"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CruceCreatedDomainEvent that = (CruceCreatedDomainEvent) o;
        return aggregateId().equals(that.aggregateId()) && estado().equals(that.estado());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId(), ruta(), estado());
    }
}
