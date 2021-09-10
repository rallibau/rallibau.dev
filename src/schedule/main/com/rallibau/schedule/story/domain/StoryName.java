package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.StringValueObject;

public class StoryName extends StringValueObject {
    public StoryName(String value) {
        super(value);
    }

    public StoryName() {
        super("");
    }
}