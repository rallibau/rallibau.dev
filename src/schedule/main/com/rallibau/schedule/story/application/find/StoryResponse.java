package com.rallibau.schedule.story.application.find;

import com.rallibau.shared.domain.bus.query.Response;

public class StoryResponse implements Response {
    private final String id;
    private final String name;
    private final String processId;


    public StoryResponse(String id, String name, String processId) {
        this.id = id;
        this.name = name;
        this.processId = processId;
    }

    public StoryResponse() {
        this.id = null;
        this.name = null;
        this.processId = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProcessId() {
        return processId;
    }
}
