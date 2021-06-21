package com.inetum.livetooling.viaje.cruce.domain;

import com.inetum.livetooling.shared.domain.WordMother;
import com.inetum.livetooling.viaje.cruce.application.create.PersistCruceCommand;

public final class CruceMother {
    public static Cruce create(CruceId id,
                               CruceRuta ruta,
                               CruceEstado estado,
                               String tagViajeId) {
        return Cruce.create(id, ruta, estado, tagViajeId);
    }

    public static Cruce fromRequest(PersistCruceCommand command) {
        return create(CruceIdMother.create(command.id()),
                CruceRutaMother.create(command.ruta()),
                CruceEstadoMother.create(CruceEstadoEnum.PENDIENTE),
                WordMother.random());
    }

    public static Cruce random() {
        return create(CruceIdMother.random(), CruceRutaMother.random(), CruceEstadoMother.random(), WordMother.random());
    }
}
