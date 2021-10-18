package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.Identifier;

public class PageIdUser extends Identifier {
    public PageIdUser(String value) {
        super(value);
    }

    public PageIdUser() {
    }

    public static PageIdUser create(String value) {
        return new PageIdUser(value);
    }
}
