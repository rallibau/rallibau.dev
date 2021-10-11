package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.WordMother;

public class PageBodyMother {
    public static PageBody random() {
        return PageBodyMother.create(WordMother.random());
    }

    private static PageBody create(String name) {
        return PageBody.create(name);
    }
}
