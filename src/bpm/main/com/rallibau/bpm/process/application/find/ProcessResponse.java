package com.rallibau.bpm.process.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.ArrayList;
import java.util.List;

public class ProcessResponse implements Response {
    private final String id;
    private final String name;
    private final List<String> nodes;


    public ProcessResponse(String id, String name, List<String> nodes) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
    }
    public ProcessResponse() {
        this.id = "";
        this.name = "";
        this.nodes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getNodes() {
        return nodes;
    }
}
