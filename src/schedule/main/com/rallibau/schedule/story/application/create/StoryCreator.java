package com.rallibau.schedule.story.application.create;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.EventBus;

@Service
public class StoryCreator {
    private final StoryRepository storyRepository;
    private final EventBus eventBus;

    public StoryCreator(StoryRepository storyRepository, EventBus eventBus) {
        this.storyRepository = storyRepository;
        this.eventBus = eventBus;
    }

    public void create(Story story) {
        storyRepository.save(story);
        eventBus.publish(story.pullDomainEvents());
    }
}
