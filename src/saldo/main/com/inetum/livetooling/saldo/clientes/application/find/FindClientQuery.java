package com.inetum.livetooling.saldo.clientes.application.find;

import com.inetum.livetooling.shared.domain.bus.query.Query;

import java.util.Optional;

public class FindClientQuery implements Query {
    private final Optional<String> id;

    public FindClientQuery(Optional<String> id) {
        this.id = id;
    }

    public Optional<String> id() {
        return id;
    }
}
