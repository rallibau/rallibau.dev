package com.rallibau.acl.user.infrastructure.persistence.inMemory;

import com.rallibau.acl.user.domain.*;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRepositoryInMemoryImpl extends MemoryRepository<User, UserId> implements UserRepository {

    public UserRepositoryInMemoryImpl() {
        super(User.class);
    }

    @Override
    public void save(User user) {
        persist(user);
    }

    @Override
    public Optional<User> get(UserId id) {
        return byId(id);
    }


    @Override
    public List<User> searchAll() {
        return all();
    }

    @Override
    public List<User> matching(Criteria criteria) {
        return byCriteria(criteria);
    }

    @Override
    public Optional<User> findByName(String userName) {
        Criteria criteria = new Criteria(new Filters(
                Collections.singletonList(Filter.create("userName", "=", userName))), Order.asc("userName"));
        List<User> users = matching(criteria);

        if (users.isEmpty()) {
            return Optional.empty();
        }

        if (users.size() > 1) {
            throw new UserDuplicated(new UserName(userName));
        }

        return Optional.of(users.get(0));
    }
}
