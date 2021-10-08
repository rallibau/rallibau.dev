package com.rallibau.acl.user.application.find;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserMother;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserFinderShould {

    private UserFinder userFinder;
    private UserRepository userRepository;


    @BeforeEach
    private void setUp() {
        userRepository = mock(UserRepository.class);
        userFinder = new UserFinder(userRepository);
    }


    @Test
    public void find_an_existing_user() {
        User user = UserMother.random();
        when(userRepository.get(user.id())).thenReturn(Optional.of(user));
        User userFind = userFinder.find(user.id().value());
        assertThat("obtnemos un nodo", userFind != null);
    }

    @Test
    public void find_an_existing_user_by_username() {
        User user = UserMother.random();
        Criteria criteria = new Criteria(new Filters(Arrays.asList(Filter.create("userName", "=", user.userName().value()))), Order.asc("userName"));

        when(userRepository.matching(criteria)).thenReturn(Arrays.asList(user));
        assertThat(Arrays.asList(user), containsInAnyOrder(userFinder.findByCriteria(criteria).toArray()));
    }




}
