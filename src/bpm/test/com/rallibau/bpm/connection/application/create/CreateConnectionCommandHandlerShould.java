package com.rallibau.bpm.connection.application.create;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CreateConnectionCommandHandlerShould {

    private CreateConnectionCommandHandler createConnectionCommandHandler;
    private ConnectionCreator connectionCreator;

    @BeforeEach
    protected void setUp() {
        connectionCreator = mock(ConnectionCreator.class);
        createConnectionCommandHandler = new CreateConnectionCommandHandler(connectionCreator);
    }

    @Test
    public void create_valid_connection() {
        Connection connection = ConnectionMother.random();
        CreateConnectionCommand createConnectionCommand = new CreateConnectionCommand(connection.id().value(),
                connection.connectionType().value(),
                connection.nodeIdOwner().value(),
                connection.nodeIdTarget().value());


        createConnectionCommandHandler.handle(createConnectionCommand);
        verify(connectionCreator, atLeastOnce()).create(connection);

    }
}
