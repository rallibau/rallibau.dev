package com.rallibau.cms.page.application.find;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageId;
import com.rallibau.cms.page.domain.PageNotExist;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.shared.domain.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PageFinder {
    private final PageRepository pageRepository;

    public PageFinder(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public Page find(String id) {
        return pageRepository.get(PageId.create(id)).orElseThrow(() -> new PageNotExist(PageId.create(id)));
    }

    public List<Page> find() {
        return pageRepository.searchAll();
    }
}
