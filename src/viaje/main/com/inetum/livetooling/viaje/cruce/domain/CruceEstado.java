package com.inetum.livetooling.viaje.cruce.domain;

import com.inetum.livetooling.shared.domain.StringValueObject;

public class CruceEstado extends StringValueObject {
    public CruceEstado(CruceEstadoEnum value) {
        super(value.name());
    }
    public CruceEstado() {
        super(CruceEstadoEnum.PENDIENTE.name());
    }
}
