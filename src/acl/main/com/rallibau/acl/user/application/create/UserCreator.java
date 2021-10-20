package com.rallibau.acl.user.application.create;

import com.rallibau.acl.user.domain.User;
import com.rallibau.acl.user.domain.UserRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.EventBus;

@Service
public class UserCreator {
    private final UserRepository userRepository;
    private final EventBus eventBus;

    public UserCreator(UserRepository userRepository, EventBus eventBus) {
        this.userRepository = userRepository;
        this.eventBus = eventBus;
    }

    public void create(User user) {
        userRepository.save(user);
        eventBus.publish(user.pullDomainEvents());
    }
}
