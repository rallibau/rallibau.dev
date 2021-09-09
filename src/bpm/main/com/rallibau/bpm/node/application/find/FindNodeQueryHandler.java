package com.rallibau.bpm.node.application.find;

import com.rallibau.bpm.connection.application.find.ConnectionFinder;
import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.NodeIdOwner;
import com.rallibau.bpm.connection.domain.NodeIdTarget;
import com.rallibau.bpm.node.domain.Node;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;
import com.rallibau.shared.domain.criteria.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindNodeQueryHandler implements QueryHandler<NodeFinderQuery, NodeResponse> {

    private final NodeFinder nodeFinder;
    private final ConnectionFinder connectionFinder;

    public FindNodeQueryHandler(NodeFinder nodeFinder, ConnectionFinder connectionFinder) {
        this.nodeFinder = nodeFinder;
        this.connectionFinder = connectionFinder;
    }

    @Override
    public NodeResponse handle(NodeFinderQuery query) {
        Optional<Node> node = nodeFinder.find(query.nodeId());
        if (node.isPresent()) {
            return new NodeResponse(
                    node.get().id().value(),
                    node.get().name().value(),
                    node.get().nodeType().value(), getConnections(node.get().id().value()));
        }
        return new NodeResponse();
    }

    private List<String> getConnections(String nodeId) {
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("nodeIdOwner",
                                        FilterOperator.EQUAL.value(),
                                        nodeId))),
                Order.asc("id"));
        List<Connection> connections = connectionFinder.find(criteria);
        return connections.stream().map(Connection::nodeIdTarget).map(NodeIdTarget::value).collect(Collectors.toList());
    }
}
