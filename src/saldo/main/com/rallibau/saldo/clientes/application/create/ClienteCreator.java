package com.rallibau.saldo.clientes.application.create;

import com.rallibau.saldo.clientes.domain.Cliente;
import com.rallibau.saldo.clientes.domain.ClienteId;
import com.rallibau.saldo.clientes.domain.ClienteName;
import com.rallibau.saldo.clientes.domain.ClienteRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.EventBus;

@Service
public final class ClienteCreator {
    private final ClienteRepository repository;
    private final EventBus eventBus;

    public ClienteCreator(ClienteRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(ClienteId id, ClienteName name) {
        Cliente cliente = Cliente.create(id,name);
        repository.save(cliente);
        eventBus.publish(cliente.pullDomainEvents());
    }
}
