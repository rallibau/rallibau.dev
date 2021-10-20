package com.rallibau.cms.user.application.pageCountIncrement;

import com.rallibau.cms.user.application.create.UserCmsCreator;
import com.rallibau.cms.user.application.find.UserCmsFinder;
import com.rallibau.cms.user.domain.User;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.DomainEventSubscriber;
import com.rallibau.shared.domain.events.cms.PageCreatedDomainEvent;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({PageCreatedDomainEvent.class})
public class PageCreatedListener {

    private final UserCmsFinder userCmsFinder;
    private final UserCmsCreator userCmsCreator;

    public PageCreatedListener(UserCmsFinder userCmsFinder, UserCmsCreator userCmsCreator) {
        this.userCmsFinder = userCmsFinder;
        this.userCmsCreator = userCmsCreator;
    }

    @EventListener
    public void on(PageCreatedDomainEvent event) {
        User user = userCmsFinder.find(event.pageIdUser());
        user.pageCountIncrement();
        userCmsCreator.create(user);
    }
}
