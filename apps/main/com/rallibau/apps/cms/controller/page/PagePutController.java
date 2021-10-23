package com.rallibau.apps.cms.controller.page;

import com.rallibau.cms.page.application.create.PageCreator;
import com.rallibau.cms.page.application.create.PageRequest;
import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageBody;
import com.rallibau.cms.page.domain.PageId;
import com.rallibau.cms.page.domain.PageTitle;
import com.rallibau.cms.user.application.find.UserCmsFinder;
import com.rallibau.cms.user.domain.User;
import com.rallibau.shared.domain.authentication.SessionInfo;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagePutController extends ApiController {

    private final PageCreator pageCreator;
    private final UserCmsFinder userCmsFinder;
    private final SessionInfo sessionInfo;

    public PagePutController(QueryBus queryBus,
                             CommandBus commandBus,
                             PageCreator pageCreator,
                             UserCmsFinder userCmsFinder,
                             SessionInfo sessionInfo) {
        super(queryBus, commandBus);
        this.pageCreator = pageCreator;
        this.userCmsFinder = userCmsFinder;
        this.sessionInfo = sessionInfo;
    }

    @PutMapping(value = "/page/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody PageRequest pageRequest

    ) throws CommandHandlerExecutionError {
        User user = userCmsFinder.find(sessionInfo.logedUserId());
        pageCreator.create(Page.create(PageId.create(id),
                user,
                PageTitle.create(pageRequest.getTitle()),
                PageBody.create(pageRequest.getBody())));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
