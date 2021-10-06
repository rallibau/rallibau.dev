package com.rallibau.apps.acl.controller;


import com.rallibau.acl.token.application.UserFinder;
import com.rallibau.acl.token.domain.TokenRequest;
import com.rallibau.acl.token.domain.TokenResponse;
import com.rallibau.acl.token.domain.TokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;


    private final TokenUtil tokenUtil;


    private final UserFinder userFinder;


    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenUtil tokenUtil,
                                    UserFinder userFinder) {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
        this.userFinder = userFinder;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userFinder
                .loadUserByUsername(authenticationRequest.getUsername());
        String token = null;

        try {
            token = tokenUtil.generateToken(userDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(new TokenResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}