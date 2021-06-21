package com.inetum.livetooling.viaje.cruce.application.create;

import com.inetum.livetooling.shared.domain.bus.command.Command;

public final class PersistCruceCommand implements Command {
    private final String id;
    private final String ruta;
    private final String tagNumero;
    private final Boolean update;

    public PersistCruceCommand(String id, String ruta, String tagNumero, Boolean update) {
        this.id = id;
        this.ruta = ruta;
        this.tagNumero = tagNumero;
        this.update = update;
    }

    public String id() {
        return id;
    }

    public String ruta() {
        return ruta;
    }

    public String tagNumero() {
        return tagNumero;
    }

    public Boolean update() {
        return update;
    }
}
