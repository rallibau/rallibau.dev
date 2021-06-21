package com.inetum.livetooling.viaje.cruce.domain;

import com.inetum.livetooling.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface CruceRepository {
    void save(Cruce cliente);

    Optional<Cruce> get(CruceId id);

    List<Cruce> matching(Criteria criteria);

}
