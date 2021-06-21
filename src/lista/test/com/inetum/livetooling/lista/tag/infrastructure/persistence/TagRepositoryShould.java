package com.inetum.livetooling.lista.tag.infrastructure.persistence;

import com.inetum.livetooling.apps.gestionLista.GestionListaApplication;
import com.inetum.livetooling.lista.tag.domain.Tag;
import com.inetum.livetooling.lista.tag.domain.TagIdMother;
import com.inetum.livetooling.lista.tag.domain.TagMother;
import com.inetum.livetooling.lista.tag.domain.TagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ContextConfiguration(classes = GestionListaApplication.class)
@SpringBootTest
public class TagRepositoryShould {
    @Autowired
    protected TagRepository repository;

    @Test
    void save_a_tag() {
        Tag tag = TagMother.random();
        repository.save(tag);
    }

    @Test
    void return_an_existing_tag() {
        Tag tag = TagMother.random();
        repository.save(tag);
        assertEquals(Optional.of(tag), repository.get(tag.id()));
    }

    @Test
    void not_return_a_non_existing_tag() {
        assertFalse(repository.get(TagIdMother.random()).isPresent());
    }
}
