package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.events.acl.UserCreatedDomainEvent;

import java.util.Objects;

public final class User extends AggregateRoot {

    private final UserId id;
    private final UserName userName;
    private final UserPassword userPassword;

    private User() {
        this.id = null;
        this.userName = null;
        this.userPassword = null;
    }

    private User(UserId id, UserName userName, UserPassword userPassword) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
    }


    public static User create(UserId id, UserName userName, UserPassword userPassword) {
        User user = new User(id, userName, userPassword);
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

    public UserPassword userPassword() {
        return userPassword;
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
                userName.equals(user.userName) && userPassword.equals(user.userPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userPassword);
    }

}
