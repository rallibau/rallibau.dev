package com.rallibau.bpm.node.application.find;

import com.rallibau.shared.domain.bus.query.Query;

public final class NodeFinderQuery implements Query {
    private final String nodeId;

    public NodeFinderQuery(String nodeId) {
        this.nodeId = nodeId;
    }

    public String nodeId() {
        return nodeId;
    }
}
