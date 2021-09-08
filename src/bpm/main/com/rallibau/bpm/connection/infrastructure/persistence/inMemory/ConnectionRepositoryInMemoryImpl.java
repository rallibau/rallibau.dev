package com.rallibau.bpm.connection.infrastructure.persistence.inMemory;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionId;
import com.rallibau.bpm.connection.domain.ConnectionRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.List;
import java.util.Optional;

public class ConnectionRepositoryInMemoryImpl extends MemoryRepository<Connection, ConnectionId> implements ConnectionRepository {

    public ConnectionRepositoryInMemoryImpl() {
        super(Connection.class);
    }

    @Override
    public void save(Connection connection) {
        persist(connection);
    }

    @Override
    public Optional<Connection> get(ConnectionId id) {
        return byId(id);
    }


    @Override
    public List<Connection> searchAll() {
        return all();
    }

    @Override
    public List<Connection> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
