package com.rallibau.apps.commons.filters;

import com.rallibau.shared.domain.authentication.TokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {


    @Value("${acl.anonymous}")
    private String anonymous;


    private final TokenUtil tokenUtil;

    public JwtRequestFilter(TokenUtil tokenUtil) {

        this.tokenUtil = tokenUtil;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,
                                    HttpServletResponse response,
                                    @NotNull FilterChain chain)
            throws ServletException, IOException {

        if (isAnonymousAccessAllowed()) {
            chain.doFilter(request, response);

        } else {
            doFilterAutenthicated(request, response, chain);
        }

    }


    private void doFilterAutenthicated(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String userName = null;
        String jwtToken = null;
        final String requestTokenHeader = getAuthorizationHeader(request);

        if (haveGoodToken(requestTokenHeader)) {
            jwtToken = requestTokenHeader.substring(7);
            userName = getUsername(jwtToken);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (tokenUtil.validateToken(jwtToken)) {
                UserDetails userDetails =  new User(userName, "", new ArrayList<>());
                HashMap<String, String> credentials = new HashMap<>();
                credentials.put(TokenUtil.USER_ID, getUserId(jwtToken));
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                credentials,
                                userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }


    private String getAuthorizationHeader(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    private boolean isAnonymousAccessAllowed() {
        return Boolean.parseBoolean(anonymous);
    }

    private String getUsername(String jwtToken) {
        String userName = null;
        try {
            userName = tokenUtil.getUsernameFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            logger.warn("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            logger.warn("JWT Token has expired");
        } catch (SignatureException e) {
            logger.warn("JWT bad signature");
        } catch (Exception e) {
            logger.warn("JWT unexpected error", e);
        }
        return userName;
    }

    private String getUserId(String jwtToken) {
        String userId = null;
        try {
            userId = tokenUtil.getUserIdFromToken(jwtToken);
        } catch (IllegalArgumentException e) {
            logger.warn("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            logger.warn("JWT Token has expired");
        } catch (SignatureException e) {
            logger.warn("JWT bad signature");
        } catch (Exception e) {
            logger.warn("JWT unexpected error", e);
        }
        return userId;
    }

    private boolean haveGoodToken(String requestTokenHeader) {
        return requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ");
    }

}