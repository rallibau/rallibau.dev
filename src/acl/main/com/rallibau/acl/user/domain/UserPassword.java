package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.StringValueObject;
import com.rallibau.shared.domain.spring.security.PasswordEncoder;

public class UserPassword extends StringValueObject {
    private UserPassword(String password) {
        super(password);
    }

    public UserPassword() {
        super("");
    }

    public static UserPassword create(PasswordEncoder passwordEncoder, String password) {
        return new UserPassword(passwordEncoder.encode(password));
    }
}
