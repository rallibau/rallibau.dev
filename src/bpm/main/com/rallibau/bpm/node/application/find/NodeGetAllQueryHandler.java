package com.rallibau.bpm.node.application.find;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeGetAllQueryHandler implements QueryHandler<NodeGetAllQuery, NodesResponse> {

    private final NodeFinder nodeFinder;

    public NodeGetAllQueryHandler(NodeFinder nodeFinder) {
        this.nodeFinder = nodeFinder;
    }

    @Override
    public NodesResponse handle(NodeGetAllQuery query) {
        List<Node> nodes = nodeFinder.find();
        List<NodeResponse> nodeResponseList = new ArrayList<>();
        NodesResponse nodesResponse = new NodesResponse(nodeResponseList);
        nodes.forEach(node -> nodesResponse.getNodes().add(new NodeResponse(
                node.id().value(),
                node.name().value(),
                node.nodeType().value())));
        return nodesResponse;
    }
}
