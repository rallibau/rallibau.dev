package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.BlobValueObject;

import java.sql.SQLException;

public final class PageBody extends BlobValueObject {
    public PageBody(String value){
        super(value);
    }

    public PageBody() throws SQLException {
        super("");
    }

    public static PageBody create(String name){
        return new PageBody(name);
    }
}
