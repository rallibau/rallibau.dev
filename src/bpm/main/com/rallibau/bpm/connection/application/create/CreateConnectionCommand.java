package com.rallibau.bpm.connection.application.create;

import com.rallibau.shared.domain.bus.command.Command;

import java.io.Serializable;
import java.util.HashMap;

public class CreateConnectionCommand extends Command {

    private final String id;
    private final String owner;
    private final String target;

    public CreateConnectionCommand(String id, String owner, String target) {
        this.id = id;
        this.owner = owner;
        this.target = target;
    }

    public CreateConnectionCommand() {
        this.id = null;
        this.owner = null;
        this.target = null;
    }

    public String id() {
        return id;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("owner", owner);
            put("target", target);
        }};
    }

    @Override
    public CreateConnectionCommand fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreateConnectionCommand(aggregateId,
                (String) body.get("owner"),
                (String) body.get("target"));
    }

    public String owner() {
        return owner;
    }

    public String target() {
        return target;
    }
}
