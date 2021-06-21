package com.inetum.livetooling.saldo.clientes.domain;

import com.inetum.livetooling.saldo.clientes.application.create.CreateClienteCommand;

public final class ClienteMother {
    public static Cliente create(ClienteId id,
                                 ClienteName name) {
        return Cliente.create(id, name);
    }

    public static Cliente fromRequest(CreateClienteCommand command) {
        return create(ClienteIdMother.create(command.id()), ClienteNameMother.create(command.name()));
    }

    public static Cliente random() {
        return create(ClienteIdMother.random(), ClienteNameMother.random());
    }
}
