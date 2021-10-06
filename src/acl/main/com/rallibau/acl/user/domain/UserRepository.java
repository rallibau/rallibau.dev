package com.rallibau.acl.user.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> get(UserId id);

    List<User> searchAll();

    List<User> matching(Criteria criteria);

}
