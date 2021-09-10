package com.rallibau.schedule.story.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface StoryRepository {
    void save(Story story);

    Optional<Story> get(StoryId id);


    List<Story> searchAll();

    List<Story> matching(Criteria criteria);
}
