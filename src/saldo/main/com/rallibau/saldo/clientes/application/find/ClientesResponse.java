package com.rallibau.saldo.clientes.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.List;

public class ClientesResponse implements Response {
    private List<ClienteResponse> clientes = null;

    public ClientesResponse(List<ClienteResponse> clientes) {
        this.clientes = clientes;
    }

    public List<ClienteResponse> getClientes() {
        return clientes;
    }

    public static class ClienteResponse{
        private final String id;
        private final String name;


        public ClienteResponse(String id, String name) {
            this.id = id;
            this.name = name;
        }


        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

}
