package com.inetum.livetooling.saldo.clientes.infrastructure.persistence;

import com.inetum.livetooling.saldo.clientes.domain.Cliente;
import com.inetum.livetooling.saldo.clientes.domain.ClienteId;
import com.inetum.livetooling.saldo.clientes.domain.ClienteRepository;
import com.rallibau.shared.domain.criteria.Criteria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

//@Service
public final class InMemoryClienteRepository implements ClienteRepository {
    private final HashMap<String, Cliente> clientes = new HashMap<>();

    @Override
    public void save(Cliente cliente) {
        clientes.put(cliente.id().value(), cliente);
    }

    @Override
    public Optional<Cliente> get(ClienteId id) {
        return Optional.ofNullable(clientes.get(id.value()));
    }

    @Override
    public List<Cliente> matching(Criteria criteria) {
        return new ArrayList<>(clientes.values());
    }
}
