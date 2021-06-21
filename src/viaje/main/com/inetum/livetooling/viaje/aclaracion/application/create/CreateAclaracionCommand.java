package com.inetum.livetooling.viaje.aclaracion.application.create;

import com.inetum.livetooling.shared.domain.bus.command.Command;

public class CreateAclaracionCommand implements Command {
    private final String id;
    private final String cruceId;

    public CreateAclaracionCommand(String id, String cruceId) {
        this.id = id;
        this.cruceId = cruceId;
    }

    public String id() {
        return id;
    }

    public String cruceId() {
        return cruceId;
    }
}
