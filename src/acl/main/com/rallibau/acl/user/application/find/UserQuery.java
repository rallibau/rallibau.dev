package com.rallibau.acl.user.application.find;

import com.rallibau.shared.domain.bus.query.Query;

public final class UserQuery implements Query {
    private final String userName;

    public UserQuery(String userName) {
        this.userName = userName;
    }

    public String userName() {
        return userName;
    }
}
