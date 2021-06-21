package com.inetum.livetooling.saldo.clientes.application;

import com.inetum.livetooling.saldo.clientes.application.create.CreateClienteCommand;
import com.inetum.livetooling.saldo.clientes.domain.ClienteId;
import com.inetum.livetooling.saldo.clientes.domain.ClienteIdMother;
import com.inetum.livetooling.saldo.clientes.domain.ClienteName;
import com.inetum.livetooling.saldo.clientes.domain.ClienteNameMother;

public final class CreateClienteCommandHandlerMother {

    public static CreateClienteCommand create(ClienteId id, ClienteName name) {
        return new CreateClienteCommand(id.value(), name.value());
    }

    public static CreateClienteCommand random(){
        return create(ClienteIdMother.random(), ClienteNameMother.random());
    }
}
