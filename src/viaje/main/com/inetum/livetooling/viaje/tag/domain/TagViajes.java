package com.inetum.livetooling.viaje.tag.domain;

import com.inetum.livetooling.shared.domain.AggregateRoot;

import java.util.Objects;

public class TagViajes extends AggregateRoot {
    private final TagViajesId id;
    private final TagViajesNumero numero;
    private final String clienteId;

    public TagViajes(TagViajesId id, TagViajesNumero numero, String clienteId) {
        this.id = id;
        this.numero = numero;
        this.clienteId = clienteId;
    }

    public TagViajes(){
        this.clienteId = null;
        this.id = null;
        this.numero = null;
    }

    public static TagViajes create(TagViajesId id, TagViajesNumero numero, String clienteId){
        return new TagViajes(id,numero, clienteId);
    }

    public TagViajesId id() {
        return id;
    }

    public TagViajesNumero numero() {
        return numero;
    }

    public String clienteId() {
        return clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagViajes tag = (TagViajes) o;
        assert id != null;
        return id.equals(tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero);
    }
}
