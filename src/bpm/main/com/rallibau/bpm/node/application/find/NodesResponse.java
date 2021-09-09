package com.rallibau.bpm.node.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.List;

public class NodesResponse implements Response {
    private final List<NodeResponse> nodes;

    public NodesResponse(List<NodeResponse> nodeResponseList) {
        this.nodes = nodeResponseList;
    }

    public List<NodeResponse> getNodes() {
        return nodes;
    }
}
