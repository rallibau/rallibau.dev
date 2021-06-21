package com.inetum.livetooling.viaje.cruce.application;

import com.inetum.livetooling.apps.gestionViaje.GestionViajeApplication;
import com.inetum.livetooling.apps.gestionViaje.command.ConsumeRabbitMqDomainEventsCommand;
import com.inetum.livetooling.shared.domain.WordMother;
import com.inetum.livetooling.viaje.cruce.application.create.CruceCreator;
import com.inetum.livetooling.viaje.cruce.domain.CruceIdMother;
import com.inetum.livetooling.viaje.cruce.domain.CruceRutaMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = GestionViajeApplication.class)
@SpringBootTest
public class CruceCreatorShould {
    @Autowired
    private CruceCreator cruceCreator;

    @Autowired
    private ConsumeRabbitMqDomainEventsCommand consumeRabbitMqDomainEventsCommand;

    @Test
    public void create_cruce_dont_failure() {
        cruceCreator.create(CruceIdMother.random(),
                CruceRutaMother.random(), WordMother.random());
    }



}
