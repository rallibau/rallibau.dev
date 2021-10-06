package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.WordMother;

public class UserNameMother {
    public static UserName random() {
        return UserNameMother.create(WordMother.random());
    }

    private static UserName create(String name) {
        return UserName.create(name);
    }
}
