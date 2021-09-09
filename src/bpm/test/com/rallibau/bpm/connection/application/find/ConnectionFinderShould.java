package com.rallibau.bpm.connection.application.find;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionRepository;
import com.rallibau.bpm.connection.domain.NodeIdOwner;
import com.rallibau.bpm.connection.domain.NodeIdOwnerMother;
import com.rallibau.shared.domain.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class ConnectionFinderShould {
    ConnectionFinder connectionFinder;
    ConnectionRepository connectionRepository;

    @BeforeEach
    private void setUp() {
        connectionRepository = mock(ConnectionRepository.class);
        connectionFinder = new ConnectionFinder(connectionRepository);
    }

    @Test
    public void find_connection_by_node_parent() {
        NodeIdOwner nodeIdOwner = NodeIdOwnerMother.random();
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("nodeIdOwner",
                                        FilterOperator.EQUAL.value(),
                                        nodeIdOwner.value()))),
                Order.asc("id"));
        List<Connection> connections = connectionFinder.find(criteria);
        verify(connectionRepository, atLeastOnce()).matching(criteria);
    }
}
