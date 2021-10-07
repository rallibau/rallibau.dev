package com.rallibau.apps.schedule.controller.story;

import com.rallibau.schedule.story.application.create.CreateStoryCommand;
import com.rallibau.schedule.story.application.create.StoryCreator;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.api.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class StoryPutController extends ApiController {

    @Autowired
    private StoryCreator storyCreator;

    public StoryPutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/story/{id}")
    public ResponseEntity<String> index(
            @PathVariable String id,
            @RequestParam String processId,
            @RequestParam String storyName
    ) throws CommandHandlerExecutionError {
        dispatch(new CreateStoryCommand(id, storyName, processId));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends RuntimeException>, HttpStatus> errorMapping() {
        return null;
    }
}
