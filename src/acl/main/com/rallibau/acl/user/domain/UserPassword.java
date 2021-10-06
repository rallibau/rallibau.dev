package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.StringValueObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserPassword extends StringValueObject {
    private UserPassword(String password) {
        super(password);
    }

    public UserPassword() {
        super("");
    }

    public static UserPassword create(BCryptPasswordEncoder bCryptPasswordEncoder,String password) {
        return new UserPassword(bCryptPasswordEncoder.encode(password));
    }
}
