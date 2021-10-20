package com.rallibau.cms.page.application.create;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.EventBus;

@Service
public class PageCreator {
    private final PageRepository pageRepository;
    private final EventBus eventBus;

    public PageCreator(PageRepository pageRepository, EventBus eventBus) {
        this.pageRepository = pageRepository;
        this.eventBus = eventBus;
    }

    public void create(Page page) {
        pageRepository.save(page);
        eventBus.publish(page.pullDomainEvents());
    }
}
