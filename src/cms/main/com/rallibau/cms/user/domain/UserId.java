package com.rallibau.cms.user.domain;

import com.rallibau.shared.domain.Identifier;

public class UserId extends Identifier {
    public UserId(String value) {
        super(value);
    }

    public UserId() {
    }

    public static UserId create(String value) {
        return new UserId(value);
    }
}
