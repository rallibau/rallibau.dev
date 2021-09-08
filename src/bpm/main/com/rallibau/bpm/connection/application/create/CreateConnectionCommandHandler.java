package com.rallibau.bpm.connection.application.create;

import com.rallibau.bpm.connection.domain.*;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandHandler;

@Service
public class CreateConnectionCommandHandler implements CommandHandler<CreateConnectionCommand> {
    private final ConnectionCreator connectionCreator;


    public CreateConnectionCommandHandler(ConnectionCreator connectionCreator) {
        this.connectionCreator = connectionCreator;
    }

    public void handle(CreateConnectionCommand createConnectionCommand) {
        Connection connection = Connection.create(new ConnectionId(createConnectionCommand.id()),
                new ConnectionType(createConnectionCommand.type()),
                new NodeIdOwner(createConnectionCommand.owner()),
                new NodeIdTarget(createConnectionCommand.target()));
        connectionCreator.create(connection);
    }
}
