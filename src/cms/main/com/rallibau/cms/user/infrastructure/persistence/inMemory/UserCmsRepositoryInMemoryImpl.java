package com.rallibau.cms.user.infrastructure.persistence.inMemory;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserId;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserCmsRepositoryInMemoryImpl extends MemoryRepository<User, UserId> implements UserCmsRepository {

    public UserCmsRepositoryInMemoryImpl() {
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


        return Optional.of(users.get(0));
    }
}
