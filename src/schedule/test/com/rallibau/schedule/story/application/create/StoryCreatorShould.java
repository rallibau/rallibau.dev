package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryMother;
import com.rallibau.schedule.story.domain.StoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class StoryCreatorShould {
    private StoryCreator storyCreator;
    private StoryRepository storyRepository;

    @BeforeEach
    private void setUp(){
        storyRepository = mock(StoryRepository.class);
        storyCreator = new StoryCreator(storyRepository);
    }

    @Test
    public void create_a_valid_story(){
        Story story = StoryMother.random();
        storyCreator.create(story);
        verify(storyRepository, atLeastOnce()).save(story);

    }
}
