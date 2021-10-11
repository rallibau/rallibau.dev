package com.rallibau.cms.page.application.create;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageMother;
import com.rallibau.cms.page.domain.PageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class PageCreatorShould {

    private PageCreator pageCreator;
    private PageRepository repository;

    @BeforeEach
    protected void setUp() {
        repository = mock(PageRepository.class);
        pageCreator = new PageCreator(repository);
    }

    @Test
    public void page_creator_dont_failure() {
        Page page = PageMother.random();
        pageCreator.create(page);
        verify(repository, atLeastOnce()).save(page);
    }
}
