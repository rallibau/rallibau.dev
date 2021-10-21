package com.rallibau.acl.token.domain;

import com.rallibau.shared.domain.bus.command.Request;

public class TokenRequest implements Request {


    private String username;
    private String password;

    public TokenRequest() {

    }

    public TokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}