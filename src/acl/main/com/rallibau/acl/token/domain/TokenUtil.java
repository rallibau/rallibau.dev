package com.rallibau.acl.token.domain;

import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public interface TokenUtil extends Serializable {
    String generateToken(UserDetails userDetails);

    String getUsernameFromToken(String token);

    Boolean validateToken(String token, UserDetails userDetails);

}
