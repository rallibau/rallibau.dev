package com.rallibau.cms.page.application.create;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.shared.domain.Service;

@Service
public class PageCreator {
    private final PageRepository pageRepository;

    public PageCreator(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public void create(Page page) {
        pageRepository.save(page);
    }
}
