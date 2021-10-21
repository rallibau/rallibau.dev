package com.rallibau.cms.page.application.find;

import com.rallibau.shared.domain.bus.query.Query;

public final class PageFinderQuery implements Query {
    private final String pageId;

    public PageFinderQuery(String pageId) {
        this.pageId = pageId;
    }

    public String pageId() {
        return pageId;
    }
}
