package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.Service;

@Service
public class StoryCreator {
    private final StoryRepository storyRepository;

    public StoryCreator(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public void create(Story story) {
        storyRepository.save(story);
    }
}
