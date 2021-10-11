package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.StringValueObject;

public final class PageTitle extends StringValueObject {
    public PageTitle(String value) {
        super(value);
    }

    public PageTitle() {
        super("");
    }

    public static PageTitle create(String name) {
        return new PageTitle(name);
    }
}