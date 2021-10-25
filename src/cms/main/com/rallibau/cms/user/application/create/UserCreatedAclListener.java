package com.rallibau.cms.user.application.create;

import com.rallibau.cms.user.domain.PageCount;
import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserId;
import com.rallibau.cms.user.domain.UserName;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEventSubscriber;
import com.rallibau.shared.domain.events.acl.UserCreatedDomainEvent;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({UserCreatedDomainEvent.class})
public class UserCreatedAclListener {

    private final UserCmsCreator userCmsCreator;

    public UserCreatedAclListener(UserCmsCreator userCmsCreator) {
        this.userCmsCreator = userCmsCreator;
    }

    @EventListener
    public void on(UserCreatedDomainEvent event) {
        userCmsCreator.create(
                User.create(UserId.create(event.aggregateId()),
                        UserName.create(event.userName()),
                        PageCount.create(0)));
    }
}
