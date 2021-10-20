package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.events.cms.PageCreatedDomainEvent;

public final class Page extends AggregateRoot {

    private final PageId id;
    private final PageIdUser pageIdUser;
    private final PageTitle pageTitle;
    private final PageBody pageBody;

    private Page(PageId id, PageIdUser pageIdUser, PageTitle pageTitle, PageBody pageBody) {
        this.id = id;
        this.pageIdUser = pageIdUser;
        this.pageTitle = pageTitle;
        this.pageBody = pageBody;
    }

    public Page() {
        this.id = null;
        this.pageIdUser = null;
        this.pageTitle = null;
        this.pageBody = null;
    }

    public static Page create(PageId id, PageIdUser pageIdUser, PageTitle pageTitle, PageBody pageBody) {
        Page page = new Page(id, pageIdUser, pageTitle, pageBody);
        page.record(new PageCreatedDomainEvent(
                id.value(),
                pageTitle.value(),
                pageIdUser.value()));
        return page;
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

    public PageIdUser pageIdUser() {
        return pageIdUser;
    }
}
