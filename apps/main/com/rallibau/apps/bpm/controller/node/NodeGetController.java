package com.rallibau.apps.bpm.controller.node;

import com.rallibau.bpm.node.application.find.NodeFinderQuery;
import com.rallibau.bpm.node.application.find.NodeResponse;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.bus.query.QueryHandlerExecutionError;
import com.rallibau.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NodeGetController extends ApiController {

    public NodeGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/node/{id}")
    public NodeResponse index(@PathVariable String id) throws QueryHandlerExecutionError {

        NodeResponse response = ask(new NodeFinderQuery(id));
        return response;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    private List<String> createNodeList(List<NodeId> nodesId) {
        return nodesId.stream().map(NodeId::value).collect(Collectors.toList());

    }


}
