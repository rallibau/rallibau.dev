package com.rallibau.acl.user.application.find;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserName;
import com.rallibau.acl.user.domain.UserNotExist;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.authentication.UserDetailResponse;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.Optional;

@Service
public class UserQueryHandler implements QueryHandler<UserQuery, UserDetailResponse> {

    private final UserFinder userFinder;

    public UserQueryHandler(UserFinder userFinder) {
        this.userFinder = userFinder;
    }

    @Override
    public UserDetailResponse handle(UserQuery query) {
        Optional<User> user = userFinder.findByName(query.userName());
        if (user.isEmpty()) {
            throw new UserNotExist(UserName.create(query.userName()));
        }
        return new UserDetailResponse(user.get().id().value(),
                user.get().userName().value(),
                user.get().userPassword().value());

    }
}
