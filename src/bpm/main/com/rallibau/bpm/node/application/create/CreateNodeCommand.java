package com.rallibau.bpm.node.application.create;

import com.rallibau.shared.domain.bus.command.Command;

public class CreateNodeCommand implements Command {
    private final String id;
    private final String name;

    public CreateNodeCommand(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String id(){
        return id;
    }

    public String name(){
        return name;
    }
}
