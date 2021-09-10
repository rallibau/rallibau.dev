package com.rallibau.schedule.story.domain;

public final class StoryMother {
    public static Story create(StoryId id, StoryName storyName, StoryProcessId storyProcessId) {
        return Story.create(id, storyName, storyProcessId);
    }

    public static Story random() {
        return create(StoryIdMother.random(), StoryNameMother.random(), StoryProcessIdMother.random());
    }
}
