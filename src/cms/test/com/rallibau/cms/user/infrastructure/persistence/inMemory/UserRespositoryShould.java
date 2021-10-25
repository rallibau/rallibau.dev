package com.rallibau.cms.user.infrastructure.persistence.inMemory;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserMother;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class UserRespositoryShould {

    private UserCmsRepositoryInMemoryImpl repository;

    @BeforeEach
    public void prepare() {
        repository = new UserCmsRepositoryInMemoryImpl();
    }


    @Test
    protected void save_a_page() {
        repository.save(UserMother.random());

    }


    @Test
    public void search_all_existing_page() {
        User user1 = UserMother.random();
        repository.save(user1);
        User user2 = UserMother.random();
        repository.save(user2);
        assertThat(Arrays.asList(user1, user2), containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria() {
        User user1 = UserMother.random();
        repository.save(user1);
        Criteria criteria = new Criteria(
                new Filters(
                        Collections.singletonList(
                                Filter.create("userName",
                                        "contains",
                                        user1.userName().value()))),
                Order.asc("userName"));
        assertThat(Collections.singletonList(user1),
                containsInAnyOrder(repository.matching(criteria).toArray()));

    }
}
