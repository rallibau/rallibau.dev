package com.rallibau.bpm.node.application.find;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.Optional;

@Service
public class FindNodeQueryHandler implements QueryHandler<NodeFinderQuery, NodeResponse> {

    private final NodeFinder nodeFinder;

    public FindNodeQueryHandler(NodeFinder nodeFinder) {
        this.nodeFinder = nodeFinder;
    }

    @Override
    public NodeResponse handle(NodeFinderQuery query) {
        Optional<Node> node = nodeFinder.find(query.nodeId());
        if (node.isPresent()) {
            return new NodeResponse(
                    node.get().id().value(),
                    node.get().name().value(),
                    node.get().nodeType().value());
        }
        return new NodeResponse();
    }
}
