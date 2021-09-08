package com.rallibau.bpm.connection.application.create;


import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionMother;
import com.rallibau.bpm.connection.domain.ConnectionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ConnectionCreatorShould {
    private ConnectionCreator connectionCreator;
    private ConnectionRepository connectionRepository;

    @BeforeEach
    private void setUp(){
        connectionRepository = mock(ConnectionRepository.class);
        connectionCreator = new ConnectionCreator(connectionRepository);
    }


    @Test
    public void create_connection(){
        Connection connection = ConnectionMother.random();
        connectionCreator.create(connection);
        verify(connectionRepository, atLeastOnce()).save(connection);

    }


}
