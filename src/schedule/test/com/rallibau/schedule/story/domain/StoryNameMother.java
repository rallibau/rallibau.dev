package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.WordMother;

public class StoryNameMother {

    public static StoryName create(String value) {
        return new StoryName(value);
    }

    public static StoryName random() {
        return new StoryName(WordMother.random());
    }
}
