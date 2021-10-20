package com.rallibau.cms.user.application.create;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.EventBus;

@Service
public class UserCmsCreator {
    private final UserCmsRepository userCmsRepository;
    private final EventBus eventBus;

    public UserCmsCreator(UserCmsRepository userCmsRepository, EventBus eventBus) {
        this.userCmsRepository = userCmsRepository;
        this.eventBus = eventBus;
    }

    public void create(User user) {
        userCmsRepository.save(user);
        eventBus.publish(user.pullDomainEvents());
    }
}

