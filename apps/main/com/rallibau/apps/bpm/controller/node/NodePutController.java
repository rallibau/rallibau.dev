package com.rallibau.apps.bpm.controller.node;

import com.rallibau.bpm.node.application.create.CreateNodeCommand;
import com.rallibau.bpm.process.application.create.CreateProcessCommand;
import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class NodePutController extends ApiController {

    public NodePutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/node/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id
    ) throws CommandHandlerExecutionError {

        dispatch(new CreateNodeCommand(id, "nombre"));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
