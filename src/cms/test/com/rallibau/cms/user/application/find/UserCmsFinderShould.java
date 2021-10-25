package com.rallibau.cms.user.application.find;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.cms.user.domain.UserMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserCmsFinderShould {

    private UserCmsFinder userCmsFinder;
    private UserCmsRepository repository;


    @BeforeEach
    private void setUp() {
        repository = mock(UserCmsRepository.class);
        userCmsFinder = new UserCmsFinder(repository);
    }


    @Test
    public void find_a_existing_user() {
        User user1 = UserMother.random();
        when(repository.get(user1.id())).thenReturn(Optional.of(user1));
        User userResponse = userCmsFinder.find(user1.id().value());
        assertThat("obtnemos un usuario", userResponse != null);
    }

}
