package com.rallibau.bpm.process.application.create;

import com.rallibau.shared.domain.bus.command.Command;

import java.util.List;

public class CreateProcessCommand implements Command {
    private final String id;
    private final String name;
    private final List<String> nodesId;

    public CreateProcessCommand(String id, String name, List<String> nodesId) {
        this.id = id;
        this.name = name;
        this.nodesId = nodesId;
    }

    public CreateProcessCommand() {
        this.id = null;
        this.name = null;
        this.nodesId = null;
    }

    public String id(){
        return id;
    }

    public String name(){
        return name;
    }

    public List<String> nodesId() {
        return nodesId;
    }
}
