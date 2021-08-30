package com.rallibau.bpm.node.application.create;

import com.rallibau.shared.domain.bus.command.Command;

public class CreateNodeCommand implements Command {
    private final String id;
    private final String name;
    private final String nodeType;

    public CreateNodeCommand(String id, String name, String nodeType) {
        this.id = id;
        this.name = name;
        this.nodeType = nodeType;
    }
    public String id(){
        return id;
    }

    public String name(){
        return name;
    }

    public String nodeType() {
        return nodeType;
    }
}
