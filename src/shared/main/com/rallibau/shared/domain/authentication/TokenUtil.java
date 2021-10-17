package com.rallibau.shared.domain.authentication;

import java.io.Serializable;

public interface TokenUtil extends Serializable {
    String generateToken(String userName);

    String getUsernameFromToken(String token);

    Boolean validateToken(String token);

}
