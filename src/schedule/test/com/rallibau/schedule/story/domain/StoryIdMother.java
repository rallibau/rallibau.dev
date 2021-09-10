package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.UuidMother;

public class StoryIdMother {

    public static StoryId random() {
        return StoryIdMother.create(UuidMother.random());
    }

    public static StoryId create(String value) {
        return new StoryId(value);
    }

}
