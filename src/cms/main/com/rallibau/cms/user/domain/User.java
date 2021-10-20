package com.rallibau.cms.user.domain;

import com.rallibau.shared.domain.AggregateRoot;

import java.util.Objects;

public final class User extends AggregateRoot {

    private final UserId id;
    private final UserName userName;
    private final PageCount pageCount;

    public User() {
        this.id = null;
        this.userName = null;
        this.pageCount = null;
    }

    private User(UserId id, UserName userName, PageCount pageCounts) {
        this.id = id;
        this.userName = userName;
        this.pageCount = pageCounts;
    }


    public static User create(UserId id, UserName userName, PageCount pageCounts) {
        return new User(id, userName, pageCounts);
    }

    @Override
    public UserId id() {
        return id;
    }

    public UserName userName() {
        return userName;
    }

    public PageCount pageCounts() {
        return pageCount;
    }

    public void pageCountIncrement() {
        assert pageCount != null;
        pageCount.increment();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id.equals(user.id) &&
                userName.equals(user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName);
    }

}
