package com.rallibau.cms.page.application.create;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageMother;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.shared.domain.bus.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PageCreatorShould {

    private PageCreator pageCreator;
    private PageRepository repository;
    private EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        repository = mock(PageRepository.class);
        eventBus = mock(EventBus.class);
        pageCreator = new PageCreator(repository, eventBus);
    }

    @Test
    public void page_creator_do_not_failure() {
        Page page = PageMother.random();
        pageCreator.create(page);
        verify(repository, atLeastOnce()).save(page);
    }

    @Test
    public void when_page_is_created_domain_event_is_pulled() {
        pageCreator.create(PageMother.random());
        verify(eventBus, atLeastOnce()).publish(anyList());
    }
}
