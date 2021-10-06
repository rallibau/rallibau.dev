package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.UuidMother;

public class UserIdMother {
    public static UserId random() {
        return UserIdMother.create(UuidMother.random());
    }

    private static UserId create(String id) {
        return UserId.create(id);
    }
}
