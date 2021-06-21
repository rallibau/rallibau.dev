package com.inetum.livetooling.apps.livetooling.controller.health_check;

import com.inetum.livetooling.shared.domain.DomainError;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HealthCheckGetController extends ApiController {
    public HealthCheckGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("/livetooling/health-check")
    public HashMap<String, String> index() {
        HashMap<String, String> status = new HashMap<>();
        status.put("application", "livetooling");
        status.put("status", "ok");

        return status;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
