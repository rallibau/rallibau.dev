package com.rallibau.apps.bpm.controller.health_check;

import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BpmHealthCheckGetController extends ApiController {
    public BpmHealthCheckGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/bpm/health-check")
    public HashMap<String, String> index() {
        HashMap<String, String> status = new HashMap<>();
        status.put("application", "bpm");
        status.put("status", "ok");

        return status;
    }

    @Override
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return null;
    }
}
