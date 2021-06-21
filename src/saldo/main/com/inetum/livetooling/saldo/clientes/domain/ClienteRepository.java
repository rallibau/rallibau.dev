package com.inetum.livetooling.saldo.clientes.domain;

import com.inetum.livetooling.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    void save(Cliente cliente);

    Optional<Cliente> get(ClienteId id);

    List<Cliente> matching(Criteria criteria);

}
