package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryMother;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.bus.event.EventBus;
import com.rallibau.shared.domain.events.scheduler.StoryCreatedDomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class CreateStoryCommandHandlerShould {
    private CreateStoryCommandHandler createStoryCommandHandler;
    private StoryCreator storyCreator;
    private StoryRepository storyRepository;
    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        eventBus = mock(EventBus.class);
        storyRepository = mock(StoryRepository.class);
        storyCreator = new StoryCreator(storyRepository, eventBus);

        createStoryCommandHandler = new CreateStoryCommandHandler(storyCreator);
    }

    @Test
    public void create_story_dont_failure() {
        Story story = StoryMother.random();

        StoryCreatedDomainEvent storyCreatedDomainEvent = new StoryCreatedDomainEvent(story.id().value(),
                story.name().value(),
                story.processId().value());

        CreateStoryCommand createStoryCommand = new CreateStoryCommand(story.id().value(),
                story.name().value(),
                story.processId().value());

        createStoryCommandHandler.handle(createStoryCommand);
        verify(storyRepository, atLeastOnce()).save(story);
        verify(eventBus, atLeastOnce()).publish(Collections.singletonList(storyCreatedDomainEvent));
    }

}
