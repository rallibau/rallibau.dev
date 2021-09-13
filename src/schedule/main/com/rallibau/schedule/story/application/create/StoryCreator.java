package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryBus;

@Service
public class StoryCreator {
    private final StoryRepository storyRepository;
    private final QueryBus queryBus;

    public StoryCreator(StoryRepository storyRepository, QueryBus queryBus) {
        this.storyRepository = storyRepository;
        this.queryBus = queryBus;
    }

    public void create(Story story) {



        storyRepository.save(story);
    }
}
