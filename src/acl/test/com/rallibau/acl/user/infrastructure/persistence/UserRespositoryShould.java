package com.rallibau.acl.user.infrastructure.persistence;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserMother;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.acl.user.infrastructure.persistence.inMemory.UserRepositoryInMemoryImpl;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class UserRespositoryShould {

    private UserRepository repository;

    @BeforeEach
    public void prepare() {
        repository = new UserRepositoryInMemoryImpl();
    }


    @Test
    protected void save_a_user() {
        repository.save(UserMother.random());

    }


    @Test
    public void search_all_existing_user() {
        User user1 = UserMother.random();
        repository.save(user1);
        User user2 = UserMother.random();
        repository.save(user2);
        assertThat(Arrays.asList(user1, user2), containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria() {
        User user = UserMother.random();
        repository.save(user);
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("userName",
                                        "contains",
                                        user.userName().value()))),
                Order.asc("name"));
        assertThat(Arrays.asList(user), containsInAnyOrder(repository.matching(criteria).toArray()));

    }
}
