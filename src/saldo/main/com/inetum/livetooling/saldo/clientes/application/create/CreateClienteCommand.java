package com.inetum.livetooling.saldo.clientes.application.create;

import com.rallibau.shared.domain.bus.command.Command;

public final class CreateClienteCommand implements Command {
    private final String id;
    private final String name;

    public CreateClienteCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

}
