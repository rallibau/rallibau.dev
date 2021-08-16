package com.rallibau.saldo.clientes.infrastructure.persistence;

import com.rallibau.saldo.clientes.ClienteModuleInfraestructureTestCase;
import com.rallibau.saldo.clientes.domain.Cliente;
import com.rallibau.saldo.clientes.domain.ClienteIdMother;
import com.rallibau.saldo.clientes.domain.ClienteMother;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ClientesRepositoryShould extends ClienteModuleInfraestructureTestCase {

    @Test
    void save_a_cliente() {
        Cliente course = ClienteMother.random();

        repository.save(course);
    }

    @Test
    void return_an_existing_cliente(){
        Cliente cliente = ClienteMother.random();
        repository.save(cliente);
        assertEquals(Optional.of(cliente),repository.get(cliente.id()));
    }

    @Test
    void not_return_a_non_existing_cliente() {
        assertFalse(repository.get(ClienteIdMother.random()).isPresent());
    }

}
