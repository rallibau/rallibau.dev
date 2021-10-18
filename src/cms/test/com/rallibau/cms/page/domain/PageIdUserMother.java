package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.UuidMother;

public class PageIdUserMother {
    public static PageIdUser random() {
        return PageIdUserMother.create(UuidMother.random());
    }

    private static PageIdUser create(String id) {
        return PageIdUser.create(id);
    }
}
