package com.rallibau.bpm.connection.application.find;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionFinder {

    private final ConnectionRepository connectionRepository;

    public ConnectionFinder(ConnectionRepository connectionRepository) {

        this.connectionRepository = connectionRepository;
    }

    public List<Connection> find(Criteria criteria) {
        ArrayList<Connection> result = new ArrayList<>();
        return connectionRepository.matching(criteria);
    }
}
