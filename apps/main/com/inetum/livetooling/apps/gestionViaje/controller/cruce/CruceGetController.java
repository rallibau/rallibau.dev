package com.inetum.livetooling.apps.gestionViaje.controller.cruce;

import com.inetum.livetooling.shared.domain.DomainError;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandlerExecutionError;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.shared.infraestructure.spring.ApiController;
import com.inetum.livetooling.viaje.cruce.application.find.CrucesResponse;
import com.inetum.livetooling.viaje.cruce.application.find.FindCruceQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class CruceGetController extends ApiController {

    Logger log = LogManager.getLogger(CruceGetController.class);

    public CruceGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/cruce")
    public @ResponseBody  CrucesResponse index() throws CommandHandlerExecutionError {
        return ask(new FindCruceQuery());
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
