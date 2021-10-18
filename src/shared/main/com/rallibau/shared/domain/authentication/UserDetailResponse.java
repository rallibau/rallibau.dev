package com.rallibau.shared.domain.authentication;

import com.rallibau.shared.domain.bus.query.Response;

public class UserDetailResponse implements Response {
    private final String userId;
    private final String userName;
    private final String password;

    public UserDetailResponse(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String userName() {
        return userName;
    }

    public String password() {
        return password;
    }

    public String userId() {
        return userId;
    }
}
