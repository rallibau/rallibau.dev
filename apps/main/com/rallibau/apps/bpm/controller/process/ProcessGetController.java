package com.rallibau.apps.bpm.controller.process;

import com.rallibau.bpm.process.application.find.ProcessFinderQuery;
import com.rallibau.bpm.process.application.find.ProcessResponse;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.bus.query.QueryHandlerExecutionError;
import com.rallibau.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProcessGetController extends ApiController {

    public ProcessGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/process")
    public List<HashMap<String, String>> index() throws QueryHandlerExecutionError {

        ProcessResponse response = ask(new ProcessFinderQuery());
        List<HashMap<String, String>> result = new ArrayList<>();
        for (Process process : response.processList()) {
            HashMap<String, String> processHash = new HashMap<>();
            processHash.put("id", process.id().value());
            processHash.put("name", process.name().value());

            result.add(processHash);
        }

        return result;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
