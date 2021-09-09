package com.rallibau.apps.bpm.controller.process;

import com.rallibau.bpm.node.application.find.NodeResponse;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.process.application.find.ProcessFindQuery;
import com.rallibau.bpm.process.application.find.ProcessGetAllQuery;
import com.rallibau.bpm.process.application.find.ProcessResponse;
import com.rallibau.bpm.process.application.find.ProcessesResponse;
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
public class ProcessGetController extends ApiController {

    public ProcessGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/process/{id}")
    public ProcessResponse byId(@PathVariable String id) throws QueryHandlerExecutionError {
        return ask(new ProcessFindQuery(id));
    }

    @GetMapping(value = "/process")
    public ProcessesResponse all() throws QueryHandlerExecutionError {
        return ask(new ProcessGetAllQuery());
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    private List<String> createNodeList(List<NodeId> nodesId) {
        return nodesId.stream().map(NodeId::value).collect(Collectors.toList());

    }


}
