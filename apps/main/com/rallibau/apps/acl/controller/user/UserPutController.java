package com.rallibau.apps.acl.controller.user;

import com.rallibau.acl.user.application.create.CreateUserCommand;
import com.rallibau.acl.user.domain.UserRequest;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserPutController extends ApiController {

    public UserPutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody UserRequest userRequest
    ) throws CommandHandlerExecutionError {
        dispatch(new CreateUserCommand(id, userRequest.getEmail(), userRequest.getPassword()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
