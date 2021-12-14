package com.rallibau.apps.cms.controller.page;

import com.rallibau.cms.page.application.find.GetAllPageQuery;
import com.rallibau.cms.page.application.find.PageFinderQuery;
import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageNotExist;
import com.rallibau.shared.domain.authentication.HasPermission;
import com.rallibau.shared.domain.authentication.Permission;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PageGetController extends ApiController {

    public PageGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/page/{id}")
    public ResponseEntity<?> index(
            @PathVariable String id
    ) {
        return ResponseEntity.ok(ask(new PageFinderQuery(id)));
    }

    @GetMapping(value = "/page")
    @HasPermission(value = Permission.READ, aggregate = Page.class)
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(ask(new GetAllPageQuery()));
    }

    @Override
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return new HashMap<>() {{
            put(PageNotExist.class, HttpStatus.NOT_FOUND);
        }};
    }
}
