package com.inetum.livetooling.viaje.cruce.infrastructure.persistence;

import com.inetum.livetooling.apps.gestionViaje.GestionViajeApplication;
import com.inetum.livetooling.viaje.cruce.domain.Cruce;
import com.inetum.livetooling.viaje.cruce.domain.CruceIdMother;
import com.inetum.livetooling.viaje.cruce.domain.CruceMother;
import com.inetum.livetooling.viaje.cruce.domain.CruceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = GestionViajeApplication.class)
@SpringBootTest
public class CruceRepositoryShould {
    @Autowired
    protected CruceRepository repository;

    @Test
    void save_a_cliente() {
        Cruce Cruce = CruceMother.random();
        repository.save(Cruce);
    }

    @Test
    void return_an_existing_cliente(){
        Cruce cruce = CruceMother.random();
        repository.save(cruce);
        assertEquals(Optional.of(cruce),repository.get(cruce.id()));
    }

    @Test
    void not_return_a_non_existing_cliente() {
        assertFalse(repository.get(CruceIdMother.random()).isPresent());
    }
}
