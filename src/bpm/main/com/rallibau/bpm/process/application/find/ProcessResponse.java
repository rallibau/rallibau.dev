package com.rallibau.bpm.process.application.find;

import com.rallibau.shared.domain.bus.query.Response;

public class ProcessResponse implements Response {
    private final String id;
    private final String name;


    public ProcessResponse(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public ProcessResponse() {
        this.id = "";
        this.name = "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
