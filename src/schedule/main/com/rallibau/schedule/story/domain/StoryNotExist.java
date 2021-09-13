package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.DomainError;

public final class StoryNotExist extends DomainError {
    public StoryNotExist(StoryId id) {
        super("story_not_exist", String.format("The story <%s> doesn't exist", id.value()));
    }
}
