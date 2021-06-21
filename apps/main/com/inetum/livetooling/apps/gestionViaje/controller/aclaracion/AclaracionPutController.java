package com.inetum.livetooling.apps.gestionViaje.controller.aclaracion;

import com.inetum.livetooling.shared.domain.DomainError;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandlerExecutionError;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.shared.infraestructure.spring.ApiController;
import com.inetum.livetooling.viaje.aclaracion.application.create.CreateAclaracionCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class AclaracionPutController extends ApiController {
    public AclaracionPutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/aclaracion/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody Request request
    ) throws CommandHandlerExecutionError {
        dispatch(new CreateAclaracionCommand(id, request.cruceId()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    static final class Request {
        private String cruceId;

        public String cruceId() {
            return cruceId;
        }

        public void setCruceId(String cruceId) {
            this.cruceId = cruceId;
        }

    }
}
