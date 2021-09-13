package com.rallibau.apps.bpm.controller.node;

import com.rallibau.bpm.node.application.find.NodeFinderQuery;
import com.rallibau.bpm.node.application.find.NodeGetAllQuery;
import com.rallibau.bpm.node.application.find.NodeResponse;
import com.rallibau.bpm.node.application.find.NodesResponse;
import com.rallibau.bpm.node.domain.NodeNotExist;
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

@RestController
public class NodeGetController extends ApiController {

    public NodeGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/node/{id}")
    public NodeResponse byId(@PathVariable String id) throws QueryHandlerExecutionError {
        return ask(new NodeFinderQuery(id));
    }

    @GetMapping(value = "/node")
    public NodesResponse all() throws QueryHandlerExecutionError {
        return ask(new NodeGetAllQuery());
    }


    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<Class<? extends DomainError>, HttpStatus>() {{
            put(NodeNotExist.class, HttpStatus.NOT_FOUND);
        }};
    }

}
