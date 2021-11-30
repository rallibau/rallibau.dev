package com.rallibau.bpm.process.application.create;

import com.rallibau.shared.domain.bus.command.Command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateProcessCommand extends Command {
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

    public String id() {
        return id;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("name", name);
        }};
    }

    @Override
    public CreateProcessCommand fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreateProcessCommand(aggregateId,
                (String) body.get("name"),
                new ArrayList<>());
    }

    public String name() {
        return name;
    }

    public List<String> nodesId() {
        return nodesId;
    }
}
