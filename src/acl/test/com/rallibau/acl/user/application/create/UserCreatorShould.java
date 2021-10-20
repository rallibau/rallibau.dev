package com.rallibau.acl.user.application.create;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserMother;
import com.rallibau.acl.user.domain.UserPasswordMother;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.shared.domain.bus.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class UserCreatorShould {

    private UserCreator userCreator;
    private UserRepository repository;
    private EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        repository = mock(UserRepository.class);
        eventBus = mock(EventBus.class);
        userCreator = new UserCreator(repository, eventBus);
    }

    @Test
    public void node_creator_dont_failure() {
        User user = UserMother.random();
        userCreator.create(user);
        verify(repository, atLeastOnce()).save(user);
    }

    @Test
    public void when_creat_a_user_the_password_is_encripted() {
        User user = UserMother.random();
        userCreator.create(user);
        verify(repository, atLeastOnce()).save(user);
        assertThat("the passsword not is encripted", UserPasswordMother.PASSWORD_ENCRYPTED.equals(user.userPassword().value()));
    }

    @Test
    public void when_user_is_created_domain_event_is_pulled() {
        userCreator.create(UserMother.random());
        verify(eventBus, atLeastOnce()).publish(anyList());
    }
}
