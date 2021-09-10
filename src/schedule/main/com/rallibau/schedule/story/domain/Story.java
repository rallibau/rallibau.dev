package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.AggregateRoot;

import java.util.Objects;

public class Story extends AggregateRoot {
    private final StoryId id;
    private final StoryName name;
    private final StoryProcessId processId;

    public Story(StoryId id, StoryName name, StoryProcessId processId) {
        this.id = id;
        this.name = name;
        this.processId = processId;
    }

    public Story() {
        this.id = null;
        this.name = null;
        this.processId = null;
    }

    public static Story create(StoryId id, StoryName storyName, StoryProcessId storyProcessId) {
        return new Story(
                id,
                storyName,
                storyProcessId);
    }

    public StoryId id() {
        return id;
    }

    public StoryName name() {
        return name;
    }

    public StoryProcessId processId() {
        return processId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Story story = (Story) o;
        return id.equals(story.id) &&
                name.equals(story.name) && processId.equals(story.processId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, processId);
    }
}
