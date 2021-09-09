package com.rallibau.bpm.node.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.ArrayList;
import java.util.List;

public class NodeResponse implements Response {
    private final String id;
    private final String name;
    private final String type;
    private final List<String> connections;


    public NodeResponse(String id, String name, String type, List<String> connections) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.connections = connections;
    }

    public NodeResponse() {
        this.connections = new ArrayList<>();
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

    public List<String> getConnections() {
        return connections;
    }
}
