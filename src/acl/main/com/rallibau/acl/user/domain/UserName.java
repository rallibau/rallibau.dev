package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.StringValueObject;

public class UserName extends StringValueObject {
    public UserName(String value) {
        super(value);
    }
    public UserName() {
        super("");
    }

    public static UserName create(String name) {
        return new UserName(name);
    }
}
