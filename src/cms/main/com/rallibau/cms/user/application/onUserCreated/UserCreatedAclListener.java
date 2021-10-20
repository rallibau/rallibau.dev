package com.rallibau.cms.user.application.onUserCreated;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserId;
import com.rallibau.cms.user.domain.UserName;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.shared.domain.Monitor;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEventSubscriber;
import com.rallibau.shared.domain.events.acl.UserCreatedDomainEvent;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({UserCreatedDomainEvent.class})
public class UserCreatedAclListener {

    private final UserCmsRepository userRepository;

    public UserCreatedAclListener(UserCmsRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    @Monitor
    public void on(UserCreatedDomainEvent event) {
        userRepository.save(
                User.create(UserId.create(event.aggregateId()),
                        UserName.create(event.userName())));
    }
}
