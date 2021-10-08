package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.DomainError;

public class UserDuplicated extends DomainError {
    public UserDuplicated(UserName userName) {
        super("user_duplicated", String.format("The user <%s> is duplicated", userName.value()));
    }
}
