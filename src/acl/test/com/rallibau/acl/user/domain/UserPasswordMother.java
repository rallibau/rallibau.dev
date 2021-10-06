package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.WordMother;

public class UserPasswordMother {
    public static UserPassword random() {
        return UserPasswordMother.create(WordMother.random());
    }

    private static UserPassword create(String passsword) {
        return UserPassword.create(passsword);
    }
}
