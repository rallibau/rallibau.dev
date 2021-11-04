package com.rallibau.cms.page.application.find;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageId;
import com.rallibau.cms.page.domain.PageNotExist;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;

import java.util.List;

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

    public List<Page> coverResume() {
        Criteria criteria = new Criteria(
                Filters.none(),
                Order.desc("pageCreationDate"));
        return pageRepository.matching(criteria);
    }
}
