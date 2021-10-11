package com.rallibau.apps.cms.controller.page;

import com.rallibau.cms.page.application.find.PageFinder;
import com.rallibau.cms.page.application.find.PageResponse;
import com.rallibau.cms.page.domain.Page;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class PageGetController extends ApiController {

    @Autowired
    private PageFinder pageFinder;

    public PageGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/page/{id}")
    public ResponseEntity<?> index(
            @PathVariable String id
    ) throws CommandHandlerExecutionError {
        Page page = pageFinder.find(id);
        return ResponseEntity.ok(new PageResponse(page.id().value(), page.pageTitle().value(), page.pageBody().value()));
    }

    @GetMapping(value = "/page")
    public ResponseEntity<?> all() throws CommandHandlerExecutionError {
        List<Page> pages = pageFinder.find();
        ArrayList<PageResponse> response = new ArrayList<>();
        if (pages.isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        pages.forEach(page -> response.add(new PageResponse(page.id().value(),
                page.pageTitle().value(),
                page.pageBody().value())));
        return ResponseEntity.ok(response);
    }


    @Override
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return new HashMap();
    }
}
