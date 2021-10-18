package com.rallibau.shared.domain.authentication;

import java.io.Serializable;

public interface TokenUtil extends Serializable {
    String USER_ID = "userId";

    String generateToken(String userI,String userName);

    String getUsernameFromToken(String token);

    String getUserIdFromToken(String token);

    Boolean validateToken(String token);

}
