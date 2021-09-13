package com.rallibau.schedule.story.application.find;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryMother;
import com.rallibau.schedule.story.domain.StoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

public class StoryFinderShould {
    private StoryFinder storyFinder;
    private StoryRepository storyRepository;
    List<Story> stories = new ArrayList<>();

    @BeforeEach
    private void setUp(){
        storyRepository = mock(StoryRepository.class);
        storyFinder = new StoryFinder(storyRepository);
        stories.add(StoryMother.random());
        stories.add(StoryMother.random());
        stories.add(StoryMother.random());
        when(storyRepository.searchAll()).thenReturn(stories);

    }

    @Test
    public void find_curses_dont_failure() {
        assertThat(stories, containsInAnyOrder(storyFinder.find().toArray()));
        verify(storyRepository, atLeastOnce()).searchAll();
    }


}
