package com.inetum.livetooling.viaje.cruce.application.create;

import com.inetum.livetooling.shared.domain.bus.command.Command;

public final class CruceChangeStateCommand implements Command {
    private final String id;
    private final String estado;

    public CruceChangeStateCommand(String id,String estado) {
        this.id = id;
        this.estado = estado;
    }

    public String id() {
        return id;
    }

    public String estado() {
        return estado;
    }


}
