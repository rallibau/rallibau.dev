package com.rallibau.bpm.process.application.find;

import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;

public final class ProcessFindQuery extends Query {
    private final String id;

    public ProcessFindQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("id", id);
        }};
    }

    @Override
    public Query fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new ProcessFindQuery((String) body.get("id"));
    }

    @Override
    public Response parseResponse(String message) {
        return null;
    }
}
