package com.rallibau.cms.user.domain;

import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.events.acl.UserCreatedDomainEvent;

import java.util.Objects;

public final class User extends AggregateRoot {

    private final UserId id;
    private final UserName userName;

    public User() {
        this.id = null;
        this.userName = null;
    }

    private User(UserId id, UserName userName) {
        this.id = id;
        this.userName = userName;
    }


    public static User create(UserId id, UserName userName) {
        User user = new User(id, userName);
        user.record(new UserCreatedDomainEvent(
                id.value(),
                userName.value()));

        return user;
    }

    @Override
    public UserId id() {
        return id;
    }

    public UserName userName() {
        return userName;
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
