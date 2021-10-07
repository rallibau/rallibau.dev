package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.StringValueObject;
import com.rallibau.shared.domain.spring.security.PasswordEncoderFactory;

public class UserPassword extends StringValueObject {
    private UserPassword(String password) {
        super(password);
    }

    public UserPassword() {
        super("");
    }

    public static UserPassword create(PasswordEncoderFactory passwordEncoderFactory, String password) {
        return new UserPassword(passwordEncoderFactory.encode(password));
    }
}
