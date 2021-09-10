package com.rallibau.schedule.story.infrastructure.persistence;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryMother;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.schedule.story.infrastructure.persistence.inMemory.StoryRepositoryInMemoryImpl;
import com.rallibau.shared.domain.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

//@SpringBootTest
//@ContextConfiguration(classes = ScheduleApplication.class)
public class StoryRepositoryShould {

    private StoryRepository storyRepository;

    @BeforeEach
    private void setUp() {
        storyRepository = new StoryRepositoryInMemoryImpl();
    }

    @Test
    public void get_all_existing_story() {
        Story story1 = StoryMother.random();
        Story story2 = StoryMother.random();
        storyRepository.save(story1);
        storyRepository.save(story2);
        assertThat(Arrays.asList(story1, story2), containsInAnyOrder(storyRepository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria() {
        Story story1 = StoryMother.random();
        storyRepository.save(story1);
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("name",
                                        FilterOperator.CONTAINS.value(),
                                        story1.name().value()))),
                Order.asc("id"));
        assertThat(Arrays.asList(story1), containsInAnyOrder(storyRepository.matching(criteria).toArray()));

    }
}
