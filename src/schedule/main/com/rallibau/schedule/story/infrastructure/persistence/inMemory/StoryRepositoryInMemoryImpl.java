package com.rallibau.schedule.story.infrastructure.persistence.inMemory;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryId;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.List;
import java.util.Optional;

public class StoryRepositoryInMemoryImpl extends MemoryRepository<Story, StoryId> implements StoryRepository {
    public StoryRepositoryInMemoryImpl() {
        super(Story.class);
    }

    @Override
    public void save(Story story) {
        persist(story);
    }

    @Override
    public Optional<Story> get(StoryId id) {
        return byId(id);
    }

    @Override
    public List<Story> searchAll() {
        return all();
    }

    @Override
    public List<Story> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
