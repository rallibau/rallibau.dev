package com.rallibau.apps.acl.controller;

import com.rallibau.acl.user.application.create.UserCreator;
import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserId;
import com.rallibau.acl.user.domain.UserName;
import com.rallibau.acl.user.domain.UserPassword;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.spring.security.PasswordEncoder;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserPutController extends ApiController {

    private final UserCreator userCreator;
    private final PasswordEncoder passwordEncoder;

    public UserPutController(QueryBus queryBus, CommandBus commandBus, UserCreator userCreator,
                             PasswordEncoder passwordEncoder) {
        super(queryBus, commandBus);
        this.userCreator = userCreator;
        this.passwordEncoder = passwordEncoder;

    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id
    ) throws CommandHandlerExecutionError {

        userCreator.create(User.create(
                UserId.create(id),
                UserName.create("rallibau@gmail.com"),
                UserPassword.create(passwordEncoder, "pamesa")));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return new HashMap<Class<? extends RuntimeException>, HttpStatus>() {{
            put(RuntimeException.class, HttpStatus.INTERNAL_SERVER_ERROR);
        }};
    }
}
