package com.rallibau.schedule.story.application.find;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryId;
import com.rallibau.schedule.story.domain.StoryNotExist;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.Service;

import java.util.List;

@Service
public class StoryFinder {
    private final StoryRepository storyRepository;

    public StoryFinder(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    public List<Story> find() {
        return storyRepository.searchAll();
    }

    public Story find(String id) throws StoryNotExist {
        return storyRepository.get(new StoryId(id)).orElseThrow(()->new StoryNotExist(new StoryId(id)));
    }
}
