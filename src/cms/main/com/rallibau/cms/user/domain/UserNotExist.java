package com.rallibau.cms.user.domain;

import com.rallibau.shared.domain.DomainError;

public class UserNotExist extends DomainError {
    public UserNotExist(UserId userId) {
        super("user_not_exist", String.format("The user <%s> doesn't exist", userId.value()));
    }
    public UserNotExist(UserName userName) {
        super("user_not_exist", String.format("The user <%s> doesn't exist", userName.value()));
    }
}
