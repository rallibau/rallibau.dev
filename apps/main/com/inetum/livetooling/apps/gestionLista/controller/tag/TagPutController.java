package com.inetum.livetooling.apps.gestionLista.controller.tag;


import com.inetum.livetooling.lista.tag.application.create.CreateTagCommand;
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
public final class TagPutController extends ApiController {
    public TagPutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/tag/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody Request request
    ) throws CommandHandlerExecutionError {
        dispatch(new CreateTagCommand(id,
                request.numero(),
                request.clienteId(),
                request.tagOperador(),
                request.tagTipo(),
                request.tagCategoria(),
                request.tagStatus(),
                request.tagHoraActualizacion(),
                request.tagFechaActualizacon(),
                request.tagSaldo()));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }

    static final class Request {
        private String numero;
        private String clienteId;
        private Integer tagOperador;
        private Integer tagTipo;
        private String tagCategoria;
        private Integer tagStatus;
        private String tagHoraActualizacion;
        private String tagFechaActualizacon;
        private String tagSaldo;

        public String numero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String clienteId() {
            return clienteId;
        }

        public void setClienteId(String clienteId) {
            this.clienteId = clienteId;
        }

        public Integer tagOperador() {
            return tagOperador;
        }

        public void tagOperador(Integer tagOperador) {
            this.tagOperador = tagOperador;
        }

        public Integer tagTipo() {
            return tagTipo;
        }

        public void tagTipo(Integer tagTipo) {
            this.tagTipo = tagTipo;
        }

        public String tagCategoria() {
            return tagCategoria;
        }

        public void tagCategoria(String tagCategoria) {
            this.tagCategoria = tagCategoria;
        }

        public Integer tagStatus() {
            return tagStatus;
        }

        public void setTagStatus(Integer tagStatus) {
            this.tagStatus = tagStatus;
        }

        public String tagHoraActualizacion() {
            return tagHoraActualizacion;
        }

        public void tagHoraActualizacion(String tagHoraActualizacion) {
            this.tagHoraActualizacion = tagHoraActualizacion;
        }

        public String tagFechaActualizacon() {
            return tagFechaActualizacon;
        }

        public void setTagFechaActualizacon(String tagFechaActualizacon) {
            this.tagFechaActualizacon = tagFechaActualizacon;
        }

        public String tagSaldo() {
            return tagSaldo;
        }

        public void setTagSaldo(String tagSaldo) {
            this.tagSaldo = tagSaldo;
        }
    }
}
