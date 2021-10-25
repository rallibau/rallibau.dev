package com.rallibau.cms.user.application.create;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.cms.user.domain.UserMother;
import com.rallibau.shared.domain.bus.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UserCmsCreatorShould {

    private UserCmsCreator userCmsCreator;
    private UserCmsRepository repository;
    private EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        repository = mock(UserCmsRepository.class);
        eventBus = mock(EventBus.class);
        userCmsCreator = new UserCmsCreator(repository, eventBus);
    }

    @Test
    public void user_creator_dont_failure() {
        User user1 = UserMother.random();
        userCmsCreator.create(user1);
        verify(repository, atLeastOnce()).save(user1);
    }
}
