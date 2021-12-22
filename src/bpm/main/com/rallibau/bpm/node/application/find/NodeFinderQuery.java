package com.rallibau.bpm.node.application.find;

import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;

public final class NodeFinderQuery extends Query {
    private final String nodeId;

    public NodeFinderQuery(String nodeId) {
        this.nodeId = nodeId;
    }

    public String nodeId() {
        return nodeId;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("nodeId", nodeId);
        }};
    }

    @Override
    public Query fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new NodeFinderQuery((String) body.get("nodeId"));
    }

    @Override
    public Response parseResponse(String message) {
        return null;
    }
}
