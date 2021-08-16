package com.rallibau.saldo.clientes;

import com.rallibau.saldo.clientes.domain.Cliente;
import com.rallibau.saldo.clientes.domain.ClienteRepository;
import com.rallibau.shared.infraestructure.UnitTestCase;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class ClienteModuleUnitTestCase extends UnitTestCase {
    protected ClienteRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();

        repository = mock(ClienteRepository.class);
    }

    public void shouldHaveSaved(Cliente cliente) {
        verify(repository, atLeastOnce()).save(cliente);
    }
}
