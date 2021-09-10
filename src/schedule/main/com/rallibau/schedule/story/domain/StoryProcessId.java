package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.Identifier;

public class StoryProcessId extends Identifier {
    public StoryProcessId(String value) {
        super(value);
    }

    public StoryProcessId() {

    }

    public static StoryProcessId create(String value) {
        return new StoryProcessId(value);
    }
}