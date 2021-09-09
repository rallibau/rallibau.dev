package com.rallibau.bpm.node.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.List;

public class NodesResponse implements Response {
    private final List<NodeResponse> nodeResponseList;

    public NodesResponse(List<NodeResponse> nodeResponseList) {
        this.nodeResponseList = nodeResponseList;
    }

    public List<NodeResponse> getNodeResponseList() {
        return nodeResponseList;
    }
}
