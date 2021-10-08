package com.rallibau.apps.acl.controller;


import com.rallibau.acl.token.application.UserDetailsFinder;
import com.rallibau.acl.token.domain.TokenRequest;
import com.rallibau.acl.token.domain.TokenResponse;
import com.rallibau.acl.token.domain.TokenUtil;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@CrossOrigin
public class AuthenticationController extends ApiController {


    private final AuthenticationManager authenticationManager;


    private final TokenUtil tokenUtil;


    private final UserDetailsFinder userDetailsFinder;

    public AuthenticationController(QueryBus queryBus,
                                    CommandBus commandBus,
                                    AuthenticationManager authenticationManager,
                                    TokenUtil tokenUtil,
                                    UserDetailsFinder userDetailsFinder) {

        super(queryBus, commandBus);
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
        this.userDetailsFinder = userDetailsFinder;

    }


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsFinder
                .loadUserByUsername(authenticationRequest.getUsername());
        String token = null;

        token = tokenUtil.generateToken(userDetails);

        if (token != null && !token.isEmpty()) {
            return ResponseEntity.ok(new TokenResponse(token));
        } else {
            throw new BadCredentialsException("user or password are bad");
        }


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