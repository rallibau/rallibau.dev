package com.inetum.livetooling.apps.gestionSaldo.controller.clientes;

import com.inetum.livetooling.saldo.clientes.application.find.ClientesResponse;
import com.inetum.livetooling.saldo.clientes.application.find.FindClientQuery;
import com.inetum.livetooling.shared.domain.DomainError;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandlerExecutionError;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.shared.infraestructure.spring.ApiController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;


@RestController
public class ClientesGetController extends ApiController {

    Logger log = LogManager.getLogger(ClientesGetController.class);

    public ClientesGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/clientes")
    public ClientesResponse index(@RequestParam(required = false) Optional<String> id) throws CommandHandlerExecutionError {

        return ask(new FindClientQuery(id));
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }


}
