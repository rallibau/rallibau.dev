package com.rallibau.bpm.node.application.create;

import com.rallibau.shared.domain.bus.command.Command;

import java.io.Serializable;
import java.util.HashMap;

public class CreateNodeCommand extends Command {
    private final String id;
    private final String name;
    private final String nodeType;

    public CreateNodeCommand(String id, String name, String nodeType) {
        this.id = id;
        this.name = name;
        this.nodeType = nodeType;
    }

    public CreateNodeCommand() {
        this.id = null;
        this.name = null;
        this.nodeType = null;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("name", name);
            put("nodeType", nodeType);
        }};
    }

    @Override
    public CreateNodeCommand fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreateNodeCommand(aggregateId,
                (String) body.get("name"),
                (String) body.get("nodeType"));
    }


    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String nodeType() {
        return nodeType;
    }
}
