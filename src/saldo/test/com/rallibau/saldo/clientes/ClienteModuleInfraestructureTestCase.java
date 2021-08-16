package com.rallibau.saldo.clientes;

import com.rallibau.saldo.ClientesContextInfraestructureTestCase;
import com.rallibau.saldo.clientes.domain.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClienteModuleInfraestructureTestCase extends ClientesContextInfraestructureTestCase {
    @Autowired
    protected ClienteRepository repository;

}
