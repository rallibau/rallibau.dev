package com.rallibau.cms.page.application.find;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

@Service
public class FindPageQueryHandler implements QueryHandler<PageFinderQuery, PageResponse> {

    private final PageFinder pageFinder;

    public FindPageQueryHandler(PageFinder pageFinder) {
        this.pageFinder = pageFinder;
    }

    @Override
    public PageResponse handle(PageFinderQuery query) {
        Page page = pageFinder.find(query.pageId());
        return new PageResponse(page.id().value(),
                page.pageTitle().value(),
                page.pageBody().value(),
                page.getCreator().userName().value()
        );
    }
}
