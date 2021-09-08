package com.rallibau.bpm.connection.infrastructure.persistence;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionMother;
import com.rallibau.bpm.connection.domain.ConnectionRepository;
import com.rallibau.bpm.connection.infrastructure.persistence.inMemory.ConnectionRepositoryInMemoryImpl;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ConnectionRespositoryShould {
    private ConnectionRepository connectionRepository;

    @BeforeEach
    public void prepare() {
        connectionRepository = new ConnectionRepositoryInMemoryImpl();
    }

    @Test
    protected void save_a_connection() {
        connectionRepository.save(ConnectionMother.random());

    }


    @Test
    public void search_all_existing_nodes() {
        Connection connection1 = ConnectionMother.random();
        connectionRepository.save(connection1);
        Connection connection2 = ConnectionMother.random();
        connectionRepository.save(connection2);
        assertThat(Arrays.asList(connection1, connection2), containsInAnyOrder(connectionRepository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria() {
        Connection connection1 = ConnectionMother.random();
        connectionRepository.save(connection1);
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("nodeIdOwner",
                                        "=",
                                        connection1.nodeIdOwner().value()))),
                Order.asc("name"));
        assertThat(Arrays.asList(connection1), containsInAnyOrder(connectionRepository.matching(criteria).toArray()));

    }
}
