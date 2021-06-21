package com.inetum.livetooling.viaje.aclaracion.domain;

public enum AclaracionEstadoEnum {
    ABIERTA("ABIERTA"),
    PENDIENTE("PENDIENTE"),
    RECIBIDA("RECIBIDA"),
    CERRADA("CERRADA"),
    ;


    private String estado;

    AclaracionEstadoEnum(String estado) {
        this.estado = estado;
    }
}
