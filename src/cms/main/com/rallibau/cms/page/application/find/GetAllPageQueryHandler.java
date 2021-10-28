package com.rallibau.cms.page.application.find;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.user.application.find.UserCmsFinder;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllPageQueryHandler implements QueryHandler<GetAllPageQuery, PagesResponse> {

    private final PageFinder pageFinder;
    private final UserCmsFinder userCmsFinder;

    public GetAllPageQueryHandler(PageFinder pageFinder, UserCmsFinder userCmsFinder) {
        this.pageFinder = pageFinder;
        this.userCmsFinder = userCmsFinder;
    }

    @Override
    public PagesResponse handle(GetAllPageQuery query) {
        PagesResponse response = new PagesResponse(new ArrayList<>());
        List<Page> pages = pageFinder.find();
        if (pages.isEmpty()) {
            return response;
        }
        pages.forEach(page -> response.getPages().add(new PageResponse(
                page.id().value(),
                page.pageTitle().value(),
                page.pageBody().value(),
                getUserName(page.pageIdUser().value()))
        ));

        return response;
    }

    private String getUserName(String idUser) {
        return userCmsFinder.find(idUser).userName().value();
    }
}
