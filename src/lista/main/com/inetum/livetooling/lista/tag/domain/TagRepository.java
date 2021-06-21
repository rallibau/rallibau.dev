package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface TagRepository {
    void save(Tag tag);

    Optional<Tag> get(TagId id);

    List<Tag> matching(Criteria criteria);

}
