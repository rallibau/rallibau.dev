package com.inetum.livetooling.viaje.tag.domain;

import com.inetum.livetooling.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface TagViajesRepository {
    void save(TagViajes tag);


    List<TagViajes> matching(Criteria criteria);

    Optional<TagViajes> get(TagViajesId id);

}
