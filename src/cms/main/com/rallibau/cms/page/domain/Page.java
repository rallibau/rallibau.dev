package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.AggregateRoot;

public final class Page extends AggregateRoot {

    private final PageId id;
    private final PageTitle pageTitle;
    private final PageBody pageBody;

    public Page(PageId id, PageTitle pageTitle, PageBody pageBody) {
        this.id = id;
        this.pageTitle = pageTitle;
        this.pageBody = pageBody;
    }

    public Page() {
        this.id = null;
        this.pageTitle = null;
        this.pageBody = null;
    }

    public static Page create(PageId id, PageTitle pageTitle, PageBody pageBody) {
        return new Page(id, pageTitle, pageBody);
    }

    public PageId id() {
        return id;
    }

    public PageTitle pageTitle() {
        return pageTitle;
    }

    public PageBody pageBody() {
        return pageBody;
    }
}
