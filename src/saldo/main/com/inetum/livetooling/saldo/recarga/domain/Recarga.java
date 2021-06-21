package com.inetum.livetooling.saldo.recarga.domain;

import com.inetum.livetooling.shared.domain.AggregateRoot;
import com.inetum.livetooling.shared.domain.events.RecargaCreatedDomainEvent;


public class Recarga extends AggregateRoot {
    private final RecargaId id;

    public Recarga(RecargaId id) {
        this.id = id;

    }
    public static Recarga create(RecargaId id){
        Recarga recarga = new Recarga(id);
        recarga.record(new RecargaCreatedDomainEvent());
        return recarga;
    }

    public RecargaId id() {
        return id;
    }
}
