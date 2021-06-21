package com.inetum.livetooling.saldo.clientes;

import com.inetum.livetooling.saldo.ClientesContextInfraestructureTestCase;
import com.inetum.livetooling.saldo.clientes.domain.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClienteModuleInfraestructureTestCase extends ClientesContextInfraestructureTestCase {
    @Autowired
    protected ClienteRepository repository;

}
