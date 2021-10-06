package com.rallibau.acl.user.infrastructure.persistence.inMemory;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserId;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

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
}
