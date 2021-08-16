package com.rallibau.saldo.clientes.application.find;

import com.rallibau.saldo.clientes.domain.ClienteId;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.query.QueryHandler;

import java.util.Optional;

@Service
public class FindClientQueryHandler implements QueryHandler<FindClientQuery, ClientesResponse> {
    private final ClientFinder clientFinder;

    public FindClientQueryHandler(ClientFinder clientFinder) {
        this.clientFinder = clientFinder;
    }

    @Override
    public ClientesResponse handle(FindClientQuery query) {
        if(query.id().isPresent()){
            ClienteId id = new ClienteId(query.id().get());
            return clientFinder.find(Optional.of(id));
        }else{
            return clientFinder.find();
        }

    }
}
