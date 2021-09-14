package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryMother;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.bus.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class StoryCreatorShould {
    private StoryCreator storyCreator;
    private StoryRepository storyRepository;
    protected EventBus eventBus;

    @BeforeEach
    private void setUp() {
        storyRepository = mock(StoryRepository.class);
        eventBus = mock(EventBus.class);
        storyCreator = new StoryCreator(storyRepository, eventBus);

    }

    @Test
    public void create_a_valid_story() {
        Story story = StoryMother.random();
        storyCreator.create(story);
        verify(storyRepository, atLeastOnce()).save(story);

    }
}
