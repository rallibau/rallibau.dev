package com.rallibau.bpm.process.application.find;

import com.rallibau.shared.domain.bus.query.Query;

public final class ProcessFindQuery implements Query {
    private final String id;

    public ProcessFindQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
