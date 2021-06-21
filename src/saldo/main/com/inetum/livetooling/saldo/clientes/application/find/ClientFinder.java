package com.inetum.livetooling.saldo.clientes.application.find;

import com.inetum.livetooling.saldo.clientes.domain.Cliente;
import com.inetum.livetooling.saldo.clientes.domain.ClienteId;
import com.inetum.livetooling.saldo.clientes.domain.ClienteRepository;
import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientFinder {
    private final ClienteRepository repository;

    public ClientFinder(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClientesResponse find() {
        return this.find(Optional.empty());
    }

    public ClientesResponse find(Optional<ClienteId> id) {
        ArrayList<ClientesResponse.ClienteResponse> listaClientes = new ArrayList<>();
        ClientesResponse clientesResponse = new ClientesResponse(listaClientes);


        List<Filter> filtersList = new ArrayList<>();
        if (id.isPresent()) {
            Filter idFilter = new Filter(new FilterField("id"),
                    FilterOperator.EQUAL,
                    new FilterValue(id.get().value()));
            filtersList.add(idFilter);
        }

        Filters filters = new Filters(filtersList);

        Criteria criteria = new Criteria(
                filters,
                Order.none(),
                Optional.empty(),
                Optional.empty()
        );
        List<Cliente> clientes = repository.matching(criteria);
        for (Cliente cliente : clientes) {
            ClientesResponse.ClienteResponse clienteResponse = new ClientesResponse.ClienteResponse(cliente.id().value(),
                    cliente.name().value());
            listaClientes.add(clienteResponse);
        }

        return clientesResponse;
    }
}
