package com.rallibau.cms.page.application.find;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageMother;
import com.rallibau.cms.page.domain.PageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PageFinderShould {

    private PageFinder pageFinder;
    private PageRepository pageRepository;


    @BeforeEach
    private void setUp() {
        pageRepository = mock(PageRepository.class);
        pageFinder = new PageFinder(pageRepository);
    }


    @Test
    public void find_a_existing_page() {
        Page page = PageMother.random();
        when(pageRepository.get(page.id())).thenReturn(Optional.of(page));
        Page pageResponse = pageFinder.find(page.id().value());
        assertThat("obtnemos una pagina", pageResponse != null);
    }

}
