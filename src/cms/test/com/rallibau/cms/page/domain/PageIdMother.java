package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.UuidMother;

public class PageIdMother {
    public static PageId random() {
        return PageIdMother.create(UuidMother.random());
    }

    private static PageId create(String id) {
        return PageId.create(id);
    }
}
