package com.rallibau.apps.schedule.controller.story;

import com.rallibau.schedule.story.application.find.StoriesResponse;
import com.rallibau.schedule.story.application.find.StoryFinder;
import com.rallibau.schedule.story.application.find.StoryResponse;
import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryNotExist;
import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.domain.bus.query.QueryHandlerExecutionError;
import com.rallibau.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class StoryGetController extends ApiController {

    @Autowired
    private StoryFinder storyFinder;

    public StoryGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping(value = "/story/{id}")
    public StoryResponse byId(@PathVariable String id) throws QueryHandlerExecutionError {
        Story story = storyFinder.find(id);

        return new StoryResponse(story.id().value(),
                story.name().value(),
                story.processId().value());

    }

    @GetMapping(value = "/story")
    public StoriesResponse all() throws QueryHandlerExecutionError {
        StoriesResponse storiesResponse = new StoriesResponse();
        List<Story> stories = storyFinder.find();
        stories.forEach(story -> storiesResponse.getStories().add(new StoryResponse(story.id().value(),
                story.name().value(),
                story.processId().value())));
        return storiesResponse;
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return new HashMap<Class<? extends DomainError>, HttpStatus>() {{
            put(StoryNotExist.class, HttpStatus.NOT_FOUND);
        }};
    }
}
