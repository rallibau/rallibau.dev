package com.rallibau.schedule.story.application.create;

import com.rallibau.shared.domain.bus.command.Command;

public class CreateStoryCommand implements Command {
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

    public String name() {
        return name;
    }

    public String processId() {
        return processId;
    }
}
