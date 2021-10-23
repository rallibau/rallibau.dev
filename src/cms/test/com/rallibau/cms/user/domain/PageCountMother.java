package com.rallibau.cms.user.domain;

import com.rallibau.shared.domain.IntegerMother;

public class PageCountMother {
    public static PageCount random() {
        return PageCountMother.create(IntegerMother.random());
    }

    private static PageCount create(Integer value) {
        return PageCount.create(value);
    }
}
