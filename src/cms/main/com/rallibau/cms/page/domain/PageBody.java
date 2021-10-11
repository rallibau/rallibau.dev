package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.StringValueObject;

public final class PageBody extends StringValueObject {
    public PageBody(String value) {
        super(value);
    }

    public PageBody() {
        super("");
    }

    public static PageBody create(String name) {
        return new PageBody(name);
    }
}
