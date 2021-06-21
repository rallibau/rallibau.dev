package com.inetum.livetooling.viaje.aclaracion.application.update;

import com.inetum.livetooling.shared.domain.bus.command.Command;

public class UpdateAclaracionCommand implements Command {
    private final String id;
    private final String cruceId;
    private final Boolean aceptar;

    public UpdateAclaracionCommand(String id, String cruceId, Boolean aceptar) {
        this.id = id;
        this.cruceId = cruceId;
        this.aceptar = aceptar;
    }

    public String id() {
        return id;
    }

    public String cruceId() {
        return cruceId;
    }

    public Boolean aceptar() {
        return aceptar;
    }
}
