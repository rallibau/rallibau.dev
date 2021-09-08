package com.rallibau.bpm.connection.application.create;

import com.rallibau.shared.domain.bus.command.Command;

public class CreateConnectionCommand implements Command {

    private final String id;
    private final String type;
    private final String owner;
    private final String target;

    public CreateConnectionCommand(String id, String type, String owner, String target) {
        this.id = id;
        this.type = type;
        this.owner = owner;
        this.target = target;
    }

    public String id() {
        return id;
    }

    public String type() {
        return type;
    }

    public String owner() {
        return owner;
    }

    public String target() {
        return target;
    }
}
