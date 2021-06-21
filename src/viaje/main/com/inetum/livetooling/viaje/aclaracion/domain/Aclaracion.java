package com.inetum.livetooling.viaje.aclaracion.domain;

import com.inetum.livetooling.shared.domain.AggregateRoot;

public class Aclaracion extends AggregateRoot {

    private final AclaracionId id;
    private final String cruceId;
    private AclaracionEstado estado;

    public Aclaracion() {
        this.id = null;
        this.cruceId = null;
        this.estado = null;
    }

    public Aclaracion(AclaracionId id, String cruceId, AclaracionEstado estado) {
        this.id = id;
        this.cruceId = cruceId;
        this.estado = estado;
    }

    public static Aclaracion create(AclaracionId id,
                                    String cruceId,
                                    AclaracionEstado estado){
        return new Aclaracion(id,cruceId,estado);

    }

    public void setEstado(AclaracionEstado estado) {
        this.estado = estado;
    }

    public AclaracionId id() {
        return id;
    }

    public String cruceId() {
        return cruceId;
    }

    public AclaracionEstado estado() {
        return estado;
    }
}
