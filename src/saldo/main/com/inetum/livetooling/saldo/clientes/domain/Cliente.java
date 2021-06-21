package com.inetum.livetooling.saldo.clientes.domain;

import com.inetum.livetooling.shared.domain.AggregateRoot;
import com.inetum.livetooling.shared.domain.events.ClienteCreatedDomainEvent;


import java.util.Objects;

public final class Cliente extends AggregateRoot {
    private final ClienteId id;
    private final ClienteName name;

    public Cliente(ClienteId id, ClienteName name) {
        this.id = id;
        this.name = name;
    }

    public Cliente() {
        this.id = null;
        this.name = null;
    }

    public static  Cliente create(ClienteId id, ClienteName name){
        Cliente cliente = new Cliente(id,name);
        cliente.record(new ClienteCreatedDomainEvent(id.value(), name.value()));
        return  cliente;
    }

    public ClienteId id() {
        return id;
    }

    public ClienteName name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id) &&
                name.equals(cliente.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
;