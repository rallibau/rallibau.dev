package com.rallibau.shared.infraestructure.authentication.jwt;

import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.authentication.SessionInfo;
import com.rallibau.shared.domain.authentication.TokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;

@Service
public class JwtSessionInfo implements SessionInfo {

    @Override
    public String logedUserId() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        HashMap<String, String> credentials = (HashMap<String, String>) authentication.getCredentials();
        return credentials.get(TokenUtil.USER_ID);
    }
}
