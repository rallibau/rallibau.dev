package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.WordMother;

public class PageTitleMother {
    public static PageTitle random() {
        return PageTitleMother.create(WordMother.random());
    }

    private static PageTitle create(String name) {
        return PageTitle.create(name);
    }
}
