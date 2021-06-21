package com.inetum.livetooling.viaje.cruce.domain;

public enum CruceEstadoEnum {
    PENDIENTE("PENDIENTE"),
    RECHAZADO("RECHAZADO"),
    REVISION("REVISION"),
    REVISION_ACLARACION("REVISION_ACLARACION"),
    DESCUENTO("DESCUENTO"),
    PENDIENTE_COBRO("PENDIENTE_COBRO"),
    COBRADO("COBRADO"),
    NO_COBRABLE("NO_COBRABLE"),
    COBRADO_ACLARACION("COBRADO_ACLARACION"),
    REVOCADO_ACLARACION("REVOCADO_ACLARACION"),
    REVOCADO_MANUAL("REVOCADO_MANUAL"),
    REVOCADO("REVOCADO"),
    ;


    private String estado;

    CruceEstadoEnum(String estado) {
        this.estado = estado;
    }
}
