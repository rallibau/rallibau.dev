package com.rallibau.schedule.story.application.create;

import com.rallibau.shared.domain.bus.command.Command;

import java.io.Serializable;
import java.util.HashMap;

public class CreateStoryCommand extends Command {
    private final String id;
    private final String name;
    private final String processId;

    public CreateStoryCommand(String id, String name, String processId) {

        this.id = id;
        this.name = name;
        this.processId = processId;
    }

    public String id() {
        return id;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<String, Serializable>() {{
            put("name", name);
            put("processId", processId);
        }};
    }

    @Override
    public CreateStoryCommand fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new CreateStoryCommand(aggregateId,
                (String) body.get("name"),
                (String) body.get("processId"));
    }

    public String name() {
        return name;
    }

    public String processId() {
        return processId;
    }
}
