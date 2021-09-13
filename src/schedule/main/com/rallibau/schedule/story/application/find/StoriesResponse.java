package com.rallibau.schedule.story.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.ArrayList;
import java.util.List;

public class StoriesResponse implements Response {
    private final List<StoryResponse> stories;

    public StoriesResponse(List<StoryResponse> stories) {
        this.stories = stories;
    }

    public StoriesResponse() {
        this.stories = new ArrayList<>();
    }

    public List<StoryResponse> getStories() {
        return stories;
    }
}
