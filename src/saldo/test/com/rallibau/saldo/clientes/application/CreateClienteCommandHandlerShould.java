package com.rallibau.saldo.clientes.application;

import com.rallibau.saldo.clientes.ClienteModuleUnitTestCase;
import com.rallibau.saldo.clientes.application.create.ClienteCreator;
import com.rallibau.saldo.clientes.application.create.CreateClienteCommand;
import com.rallibau.saldo.clientes.application.create.CreateClienteCommandHandler;
import com.rallibau.saldo.clientes.domain.Cliente;
import com.rallibau.saldo.clientes.domain.ClienteCreatedDomainEventMother;
import com.rallibau.saldo.clientes.domain.ClienteMother;
import com.rallibau.shared.domain.events.ClienteCreatedDomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class CreateClienteCommandHandlerShould extends ClienteModuleUnitTestCase {

    private CreateClienteCommandHandler handler;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        handler = new CreateClienteCommandHandler(new ClienteCreator(repository, eventBus));
    }


    @Test
    void create_valid_cliente(){
        CreateClienteCommand command = CreateClienteCommandHandlerMother.random();
        Cliente cliente = ClienteMother.fromRequest(command);
        ClienteCreatedDomainEvent domainEvent = ClienteCreatedDomainEventMother.fromCliente(cliente);

        handler.handle(command);

        shouldHaveSaved(cliente);
        shouldHavePublished(domainEvent);

    }

}
