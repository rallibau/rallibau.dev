package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.events.cms.PageCreatedDomainEvent;

import java.util.Objects;

public final class Page extends AggregateRoot {

    private final PageId id;
    private final PageIdUser pageIdUser;
    private final PageTitle pageTitle;
    private final PageBody pageBody;
    private final PageCreationDate pageCreationDate;

    private Page(PageId id, PageCreationDate pageCreationDate, PageIdUser pageIdUser, PageTitle pageTitle, PageBody pageBody) {
        this.id = id;
        this.pageIdUser = pageIdUser;
        this.pageTitle = pageTitle;
        this.pageBody = pageBody;
        this.pageCreationDate = pageCreationDate;
    }

    public Page() {
        this.id = null;
        this.pageIdUser = null;
        this.pageTitle = null;
        this.pageBody = null;
        this.pageCreationDate = null;
    }

    public static Page create(PageId id, PageCreationDate pageCreationDate, PageIdUser pageIdUser, PageTitle pageTitle, PageBody pageBody) {
        Page page = new Page(id, pageCreationDate, pageIdUser, pageTitle, pageBody);
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

    public PageCreationDate pageCreationDate() {
        return pageCreationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Page page = (Page) o;
        return id.equals(page.id) &&
                pageTitle().value().equals(page.pageTitle().value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pageTitle);
    }
}
