package com.inetum.livetooling.apps.gestionSaldo.controller.clientes;

import com.inetum.livetooling.saldo.application.recargasManuales.CreateRecargaCommand;
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
public class RecargaPutController extends ApiController {
    public RecargaPutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/recarga/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody RecargaPutController.Request request
    ) throws CommandHandlerExecutionError {
        dispatch(new CreateRecargaCommand(id));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    static final class Request {
        private String cantidad;

        public String cantidad() {
            return cantidad;
        }

        public void setCantidad(String name) {
            this.cantidad = name;
        }
    }

}
