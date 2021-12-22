package com.rallibau.bpm.node.application.find;

import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;

public final class NodeGetAllQuery extends Query {

    public NodeGetAllQuery() {

    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>();
    }

    @Override
    public Query fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new NodeGetAllQuery();
    }

    @Override
    public Response parseResponse(String message) {
        return null;
    }

}
