package com.rallibau.cms.page.domain;

import com.rallibau.cms.user.domain.User;
import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.events.cms.PageCreatedDomainEvent;

import java.util.Objects;

public final class Page extends AggregateRoot {

    private final PageId id;
    private User user;
    private final PageTitle pageTitle;
    private final PageBody pageBody;
    private final PageCreationDate pageCreationDate;

    private Page(PageId id,
                 PageCreationDate pageCreationDate,
                 User user,
                 PageTitle pageTitle,
                 PageBody pageBody) {
        this.id = id;
        this.user = user;
        this.pageTitle = pageTitle;
        this.pageBody = pageBody;
        this.pageCreationDate = pageCreationDate;
    }

    public Page() {
        this.id = null;
        this.user = null;
        this.pageTitle = null;
        this.pageBody = null;
        this.pageCreationDate = null;
    }

    public static Page create(PageId id, PageCreationDate pageCreationDate, User user, PageTitle pageTitle, PageBody pageBody) {
        Page page = new Page(id, pageCreationDate, user, pageTitle, pageBody);
        page.record(new PageCreatedDomainEvent(
                id.value(),
                pageTitle.value(),
                user.id().value()));
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
