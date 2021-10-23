package com.rallibau.acl.user.infrastructure.persistence.hibernate;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserMother;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.apps.acl.AclApplication;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ContextConfiguration(classes = AclApplication.class)
@SpringBootTest
public class UserRespositoryShould {

    @Autowired
    private UserRepository repository;


    @Test
    protected void save_a_user() {
        repository.save(UserMother.random());

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
                Order.asc("userName"));
        assertThat(Collections.singletonList(user), containsInAnyOrder(repository.matching(criteria).toArray()));
    }

    @Test
    public void search_by_test() {
        User user = UserMother.random();
        repository.save(user);
        assertThat("obtnemos un usuario", repository.findByName(user.userName().value()).isPresent());
    }
}
