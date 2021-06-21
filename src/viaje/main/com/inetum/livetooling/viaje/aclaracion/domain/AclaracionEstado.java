package com.inetum.livetooling.viaje.aclaracion.domain;

import com.inetum.livetooling.shared.domain.StringValueObject;

public class AclaracionEstado extends StringValueObject {
    public AclaracionEstado(AclaracionEstadoEnum value) {
        super(value.name());
    }
    public AclaracionEstado() {
        super(AclaracionEstadoEnum.PENDIENTE.name());
    }
}
