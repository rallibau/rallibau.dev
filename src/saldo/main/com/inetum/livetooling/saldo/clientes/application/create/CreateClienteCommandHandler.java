package com.inetum.livetooling.saldo.clientes.application.create;

import com.inetum.livetooling.saldo.clientes.domain.ClienteId;
import com.inetum.livetooling.saldo.clientes.domain.ClienteName;
import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateClienteCommandHandler implements CommandHandler<CreateClienteCommand> {

    private final ClienteCreator clienteCreator;

    public CreateClienteCommandHandler(ClienteCreator clienteCreator) {
        this.clienteCreator = clienteCreator;
    }

    @Override
    public void handle(CreateClienteCommand command) {
        ClienteId id = new ClienteId(command.id());
        ClienteName name = new ClienteName(command.name());

        clienteCreator.create(id,name);

    }
}
