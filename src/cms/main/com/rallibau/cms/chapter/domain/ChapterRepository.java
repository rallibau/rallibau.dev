package com.rallibau.cms.chapter.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository {
    void save(Chapter chapter);

    Optional<Chapter> get(ChapterId id);

    List<Chapter> searchAll();

    List<Chapter> matching(Criteria criteria);

}
