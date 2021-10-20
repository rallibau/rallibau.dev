package com.rallibau.cms.user.domain;

import com.rallibau.shared.domain.IntegerValueObject;

public class PageCount extends IntegerValueObject {
    public PageCount() {
        super(0);
    }

    public PageCount(Integer value) {
        super(value);
    }

    public static PageCount create(Integer value) {
        return new PageCount(value);
    }

    public void increment() {
        value(value() + 1);
    }
}
