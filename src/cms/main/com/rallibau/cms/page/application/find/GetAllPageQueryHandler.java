package com.rallibau.cms.page.application.find;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllPageQueryHandler implements QueryHandler<GetAllPageQuery, PagesResponse> {

    private final PageFinder pageFinder;

    public GetAllPageQueryHandler(PageFinder pageFinder) {
        this.pageFinder = pageFinder;
    }

    @Override
    public PagesResponse handle(GetAllPageQuery query) {
        PagesResponse response = new PagesResponse(new ArrayList<>());
        List<Page> pages = pageFinder.coverResume();
        if (pages.isEmpty()) {
            return response;
        }
        pages.forEach(page -> response.getPages().add(new PageResponse(
                page.id().value(),
                page.pageTitle().value(),
                page.pageBody().value(),
                page.getUser().userName().value()
        )));

        return response;
    }
}
