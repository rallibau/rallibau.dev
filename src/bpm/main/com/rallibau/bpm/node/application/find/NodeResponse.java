package com.rallibau.bpm.node.application.find;

import com.rallibau.shared.domain.bus.query.Response;

public class NodeResponse implements Response {
    private final String id;
    private final String name;
    private final String type;


    public NodeResponse(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public NodeResponse() {
        this.id = "";
        this.name = "";
        this.type = "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
