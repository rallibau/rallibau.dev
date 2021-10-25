package com.rallibau.cms.page.domain;

import java.util.Date;

public class PageCreationDateMother {
    public static PageCreationDate random() {
        return PageCreationDate.create(new Date());
    }
}
