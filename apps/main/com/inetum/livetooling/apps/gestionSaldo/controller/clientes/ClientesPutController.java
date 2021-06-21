package com.inetum.livetooling.apps.gestionSaldo.controller.clientes;

import com.inetum.livetooling.saldo.clientes.application.create.CreateClienteCommand;
import com.inetum.livetooling.shared.domain.DomainError;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandlerExecutionError;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.shared.infraestructure.spring.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public final class ClientesPutController extends ApiController {
    public ClientesPutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/clientes/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody Request request
    ) throws CommandHandlerExecutionError {
        dispatch(new CreateClienteCommand(id, request.name()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    static final class Request {
        private String name;

        public String name() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }
}
