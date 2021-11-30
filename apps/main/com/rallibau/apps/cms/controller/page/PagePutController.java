package com.rallibau.apps.cms.controller.page;

import com.rallibau.cms.page.application.create.CreatePageCommand;
import com.rallibau.cms.page.application.create.PageRequest;
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

import java.time.ZonedDateTime;

@RestController
public class PagePutController extends ApiController {

    private final SessionInfo sessionInfo;

    public PagePutController(QueryBus queryBus,
                             CommandBus commandBus,
                             SessionInfo sessionInfo) {
        super(queryBus, commandBus);
        this.sessionInfo = sessionInfo;
    }

    @PutMapping(value = "/page/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestBody PageRequest pageRequest

    ) throws CommandHandlerExecutionError {
        dispatch(
                new CreatePageCommand(
                        id,
                        ZonedDateTime.now(),
                        sessionInfo.logedUserId(),
                        pageRequest.getTitle(),
                        pageRequest.getBody()
                )
        );
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
