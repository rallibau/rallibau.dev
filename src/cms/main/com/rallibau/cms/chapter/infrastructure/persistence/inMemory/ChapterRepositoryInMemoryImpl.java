package com.rallibau.cms.chapter.infrastructure.persistence.inMemory;

import com.rallibau.cms.chapter.domain.Chapter;
import com.rallibau.cms.chapter.domain.ChapterId;
import com.rallibau.cms.chapter.domain.ChapterRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.List;
import java.util.Optional;

public class ChapterRepositoryInMemoryImpl extends MemoryRepository<Chapter, ChapterId> implements ChapterRepository{
    public ChapterRepositoryInMemoryImpl() {
        super(Chapter.class);
    }

    @Override
    public void save(Chapter chapter) {
        persist(chapter);
    }

    @Override
    public Optional<Chapter> get(ChapterId id) {
        return byId(id);
    }

    @Override
    public List<Chapter> searchAll() {
        return all();
    }

    @Override
    public List<Chapter> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
