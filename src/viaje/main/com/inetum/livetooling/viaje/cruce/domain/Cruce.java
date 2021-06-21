package com.inetum.livetooling.viaje.cruce.domain;

import com.inetum.livetooling.shared.domain.AggregateRoot;
import com.inetum.livetooling.shared.domain.events.viaje.CruceCreatedDomainEvent;

import java.util.Objects;

public class Cruce extends AggregateRoot {
    private final CruceId id;
    private CruceRuta ruta;
    private CruceEstado estado;
    private String tagNumero;

    public Cruce() {
        this.tagNumero = null;
        this.id = null;
        this.ruta = null;
        this.estado = null;
    }

    public Cruce(CruceId id, CruceRuta ruta, CruceEstado estado, String tagNumero) {
        this.id = id;
        this.ruta = ruta;
        this.estado = estado;
        this.tagNumero = tagNumero;
    }

    public static Cruce create(CruceId id,
                               CruceRuta ruta,
                               CruceEstado estado,
                               String tagViajeId){
        Cruce cruce = new Cruce(id,ruta,estado, tagViajeId);
        cruce.record(new CruceCreatedDomainEvent(id.value(),ruta.value(),estado.value()));
        return cruce;
    }

    public CruceId id(){
        return id;
    }

    public CruceRuta ruta(){
        return ruta;
    }

    public CruceEstado estado() {
        return estado;
    }

    public String tagNumero(){
        return tagNumero;
    }

    public void setEstado(CruceEstado estado) {
        this.estado = estado;
    }

    public void setRuta(CruceRuta ruta) {
        this.ruta = ruta;
    }

    public void setTagNumero(String tagNumero) {
        this.tagNumero = tagNumero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cruce cruce = (Cruce) o;
        assert id != null;
        return id.equals(cruce.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ruta);
    }
}
