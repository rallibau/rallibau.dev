package com.rallibau.saldo.clientes.application;

import com.rallibau.saldo.clientes.application.create.CreateClienteCommand;
import com.rallibau.saldo.clientes.domain.ClienteId;
import com.rallibau.saldo.clientes.domain.ClienteIdMother;
import com.rallibau.saldo.clientes.domain.ClienteName;
import com.rallibau.saldo.clientes.domain.ClienteNameMother;

public final class CreateClienteCommandHandlerMother {

    public static CreateClienteCommand create(ClienteId id, ClienteName name) {
        return new CreateClienteCommand(id.value(), name.value());
    }

    public static CreateClienteCommand random(){
        return create(ClienteIdMother.random(), ClienteNameMother.random());
    }
}
