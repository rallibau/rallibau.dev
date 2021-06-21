package com.inetum.livetooling.viaje.aclaracion.domain;

import com.inetum.livetooling.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface AclaracionRepository {
    void save(Aclaracion aclaracion);

    Optional<Aclaracion> get(AclaracionId id);

    List<Aclaracion> matching(Criteria criteria);

}
