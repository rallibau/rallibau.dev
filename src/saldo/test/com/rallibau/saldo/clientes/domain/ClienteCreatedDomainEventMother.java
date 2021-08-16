package com.rallibau.saldo.clientes.domain;

import com.rallibau.shared.domain.events.ClienteCreatedDomainEvent;

public class ClienteCreatedDomainEventMother {

    public static ClienteCreatedDomainEvent create(ClienteId id,ClienteName name){
        return new ClienteCreatedDomainEvent(id.value(),name.value());

    }

    public static ClienteCreatedDomainEvent fromCliente(Cliente cliente){
        return create(cliente.id(),cliente.name());

    }

    public static ClienteCreatedDomainEvent random(){
        return create(ClienteIdMother.random(),ClienteNameMother.random());

    }


}
