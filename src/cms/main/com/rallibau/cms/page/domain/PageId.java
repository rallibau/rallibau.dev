package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.Identifier;

public class PageId extends Identifier {
    public PageId(String value) {
        super(value);
    }

    public PageId() {
    }

    public static PageId create(String value) {
        return new PageId(value);
    }
}
