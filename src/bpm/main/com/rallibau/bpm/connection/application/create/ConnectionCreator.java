package com.rallibau.bpm.connection.application.create;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionRepository;
import com.rallibau.shared.domain.Service;

@Service
public class ConnectionCreator {
    private final ConnectionRepository connectionRepository;

    public ConnectionCreator(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public void create(Connection connection) {
        connectionRepository.save(connection);
    }
}
