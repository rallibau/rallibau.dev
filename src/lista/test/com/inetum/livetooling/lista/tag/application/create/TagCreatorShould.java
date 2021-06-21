package com.inetum.livetooling.lista.tag.application.create;

import com.inetum.livetooling.apps.gestionLista.GestionListaApplication;
import com.inetum.livetooling.lista.tag.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GestionListaApplication.class)
@SpringBootTest
public class TagCreatorShould {
    @Autowired
    private TagCreator tagCreator;

    @Test
    public void create_tag_dont_failure(){
        tagCreator.create(TagIdMother.random(),
                TagNumeroMother.random(),
                TagClienteIdMother.random(),
                TagOperadorMother.random(),
                TagTipoMother.random(),
                TagCategoriaMother.random(),
                TagStatusMother.random(),
                TagHoraActualizacionMother.random(),
                TagFechaActualizacionMother.random(),
                TagSaldoMother.random());
    }
}
