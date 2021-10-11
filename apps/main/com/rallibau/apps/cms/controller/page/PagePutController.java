package com.rallibau.apps.cms.controller.page;

import com.rallibau.cms.page.application.create.PageCreator;
import com.rallibau.cms.page.application.create.PageRequest;
import com.rallibau.cms.page.domain.*;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class PagePutController extends ApiController {

    @Autowired
    private PageCreator pageCreator;

    public PagePutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/page/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody PageRequest pageRequest

    ) throws CommandHandlerExecutionError {
        pageCreator.create(Page.create(PageId.create(id),
                PageTitle.create(pageRequest.getTitle()),
                PageBody.create(pageRequest.getBody())));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return new HashMap();
    }
}
