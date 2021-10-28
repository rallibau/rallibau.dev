package com.rallibau.cms.page.application.find;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.user.application.find.UserCmsFinder;
import com.rallibau.cms.user.domain.User;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

@Service
public class FindPageQueryHandler implements QueryHandler<PageFinderQuery, PageResponse> {

    private final PageFinder pageFinder;
    private final UserCmsFinder userCmsFinder;

    public FindPageQueryHandler(PageFinder pageFinder,
                                UserCmsFinder userCmsFinder) {
        this.pageFinder = pageFinder;
        this.userCmsFinder = userCmsFinder;
    }

    @Override
    public PageResponse handle(PageFinderQuery query) {
        Page page = pageFinder.find(query.pageId());
        User user = userCmsFinder.find(page.pageIdUser().value());
        return new PageResponse(page.id().value(),
                page.pageTitle().value(),
                page.pageBody().value(),
                user.userName().value()
        );
    }
}
