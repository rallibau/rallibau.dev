package com.inetum.livetooling.shared.domain.events.lista;

import com.inetum.livetooling.shared.domain.bus.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class TagReceivedDomainEvent extends DomainEvent {
    private final String numero;
    private final String clienteId;
    private final Integer tagOperador;
    private final Integer tagTipo;
    private final String tagCategoria;
    private final Integer tagStatus;
    private final String tagHoraActualizacion;
    private final String tagFechaActualizacon;
    private final String tagSaldo;

    public TagReceivedDomainEvent(String aggregateId, String numero, String clienteId, Integer tagOperador, Integer tagTipo, String tagCategoria, Integer tagStatus, String tagHoraActualizacion, String tagFechaActualizacon, String tagSaldo) {
        super(aggregateId);
        this.numero = numero;
        this.clienteId = clienteId;
        this.tagOperador = tagOperador;
        this.tagTipo = tagTipo;
        this.tagCategoria = tagCategoria;
        this.tagStatus = tagStatus;
        this.tagHoraActualizacion = tagHoraActualizacion;
        this.tagFechaActualizacon = tagFechaActualizacon;
        this.tagSaldo = tagSaldo;
    }

    public TagReceivedDomainEvent() {
        super(null);
        this.numero = null;
        this.clienteId = null;
        this.tagOperador = null;
        this.tagTipo = null;
        this.tagCategoria = null;
        this.tagStatus = null;
        this.tagHoraActualizacion = null;
        this.tagFechaActualizacon = null;
        this.tagSaldo = null;
    }

    public String numero() {
        return numero;
    }

    public String clienteId() {
        return clienteId;
    }



    public Integer tagOperador() {
        return tagOperador;
    }

    public Integer tagTipo() {
        return tagTipo;
    }

    public String tagCategoria() {
        return tagCategoria;
    }

    public Integer tagStatus() {
        return tagStatus;
    }

    public String tagHoraActualizacion() {
        return tagHoraActualizacion;
    }

    public String tagFechaActualizacon() {
        return tagFechaActualizacon;
    }

    public String tagSaldo() {
        return tagSaldo;
    }

    @Override
    public String eventName() {
        return "tag.received";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("numero", numero);
            put("clienteId", clienteId);
            put("tagOperador", tagOperador);
            put("tagTipo", tagTipo);
            put("tagCategoria", tagCategoria);
            put("tagStatus", tagStatus);
            put("tagHoraActualizacion", tagHoraActualizacion);
            put("tagFechaActualizacon", tagFechaActualizacon);
            put("tagSaldo", tagSaldo);
        }};
    }

    @Override
    public TagReceivedDomainEvent fromPrimitives(String aggregateId,
                                                 HashMap<String, Serializable> body,
                                                 String eventId,
                                                 String occurredOn) {
        return new TagReceivedDomainEvent(aggregateId,
                (String) body.get("numero"),
                (String) body.get("clienteId"),
                (Integer) body.get("tagOperador"),
                (Integer) body.get("tagTipo"),
                (String) body.get("tagCategoria"),
                (Integer) body.get("tagStatus"),
                (String) body.get("tagHoraActualizacion"),
                (String) body.get("tagFechaActualizacon"),
                (String) body.get("tagSaldo")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagReceivedDomainEvent that = (TagReceivedDomainEvent) o;
        return aggregateId().equals(that.aggregateId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(aggregateId());
    }
}
