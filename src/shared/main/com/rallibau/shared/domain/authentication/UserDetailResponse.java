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

    public UserDetailResponse() {
        this.userId = null;
        this.userName = null;
        this.password = null;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUserId() {
        return userId;
    }
}
