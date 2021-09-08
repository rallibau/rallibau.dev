package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface ConnectionRepository {
    void save(Connection connection);

    Optional<Connection> get(ConnectionId id);

    List<Connection> searchAll();

    List<Connection> matching(Criteria criteria);
}
