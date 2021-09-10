package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.UuidMother;

public class StoryProcessIdMother {
    public static StoryProcessId random() {
        return StoryProcessId.create(UuidMother.random());
    }

    public static StoryProcessId create(String value) {
        return StoryProcessId.create(value);
    }
}
