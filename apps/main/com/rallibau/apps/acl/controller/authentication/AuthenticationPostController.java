package com.rallibau.apps.acl.controller.authentication;


import com.rallibau.acl.token.domain.TokenRequest;
import com.rallibau.acl.token.domain.TokenResponse;
import com.rallibau.acl.user.application.find.UserQuery;
import com.rallibau.shared.domain.authentication.TokenUtil;
import com.rallibau.shared.domain.authentication.UserDetailResponse;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class AuthenticationPostController extends ApiController {
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;

    public AuthenticationPostController(QueryBus queryBus,
                                        CommandBus commandBus,
                                        AuthenticationManager authenticationManager,
                                        TokenUtil tokenUtil) {
        super(queryBus, commandBus);
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest) {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        UserDetailResponse userDetailResponse = ask(new UserQuery(authenticationRequest.getUsername()));
        String token = tokenUtil.generateToken(userDetailResponse);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

    @Override
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return new HashMap<Class<? extends RuntimeException>, HttpStatus>() {{
            put(DisabledException.class, HttpStatus.UNAUTHORIZED);
            put(BadCredentialsException.class, HttpStatus.UNAUTHORIZED);
        }};
    }
}