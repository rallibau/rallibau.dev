package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.DateValueObject;

import java.util.Date;

public class PageCreationDate extends DateValueObject {
    public PageCreationDate(Date value) {
        super(value);
    }

    public PageCreationDate() {
        super(null);
    }

    public static PageCreationDate create(Date date) {
        return new PageCreationDate(date);
    }
}
