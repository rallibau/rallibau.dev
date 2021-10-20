package com.rallibau.cms.user.infrastructure.persistence.hibernate;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserId;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import com.rallibau.shared.infraestructure.persistence.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional("cms-transaction_manager")
public class UserCmsRepositoryImp extends HibernateRepository<User> implements UserCmsRepository {

    public UserCmsRepositoryImp(@Qualifier("cms-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
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
