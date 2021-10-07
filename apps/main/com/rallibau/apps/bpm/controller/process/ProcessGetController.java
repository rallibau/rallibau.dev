package com.rallibau.apps.bpm.controller.process;

import com.rallibau.bpm.process.application.find.ProcessFindQuery;
import com.rallibau.bpm.process.application.find.ProcessGetAllQuery;
import com.rallibau.bpm.process.application.find.ProcessResponse;
import com.rallibau.bpm.process.application.find.ProcessesResponse;
import com.rallibau.bpm.process.domain.ProcessNotExist;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.bus.query.QueryHandlerExecutionError;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return new HashMap<Class<? extends RuntimeException>, HttpStatus>() {{
            put(ProcessNotExist.class, HttpStatus.NOT_FOUND);
        }};
    }
}
