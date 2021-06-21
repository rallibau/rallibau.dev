package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.AggregateRoot;
import com.inetum.livetooling.shared.domain.events.lista.TagCreatedDomainEvent;

import java.util.Objects;

public class Tag extends AggregateRoot {
    private final TagId id;
    private final TagNumero numero;
    private final TagClienteId clienteId;
    private final TagOperador tagOperador;
    private final TagTipo tagTipo;
    private final TagCategoria tagCategoria;
    private final TagStatus tagStatus;
    private final TagHoraActualizacion tagHoraActualizacion;
    private final TagFechaActualizacion tagFechaActualizacion;
    private final TagSaldo tagSaldo;

    public Tag(TagId id, TagNumero numero, TagClienteId clienteId, TagOperador tagOperador,
               TagTipo tagTipo, TagCategoria tagCategoria, TagStatus tagStatus,
               TagHoraActualizacion tagHoraActualizacion, TagFechaActualizacion tagFechaActualizacion,
               TagSaldo tagSaldo) {
        this.id = id;
        this.numero = numero;
        this.clienteId = clienteId;
        this.tagOperador = tagOperador;
        this.tagTipo = tagTipo;
        this.tagCategoria = tagCategoria;
        this.tagStatus = tagStatus;
        this.tagHoraActualizacion = tagHoraActualizacion;
        this.tagFechaActualizacion = tagFechaActualizacion;
        this.tagSaldo = tagSaldo;
    }

    public Tag(){
        this.tagSaldo = null;
        this.tagOperador = null;
        this.tagTipo = null;
        this.tagCategoria = null;
        this.tagStatus = null;
        this.tagHoraActualizacion = null;
        this.tagFechaActualizacion = null;
        this.clienteId = null;
        this.id = null;
        this.numero = null;
    }

    public static Tag create(TagId id, TagNumero numero, TagClienteId clienteId, TagOperador tagOperador,
                             TagTipo tagTipo, TagCategoria tagCategoria, TagStatus tagStatus,
                             TagHoraActualizacion tagHoraActualizacion, TagFechaActualizacion tagFechaActualizacion,
                             TagSaldo tagSaldo){
        Tag tag = new Tag(id,numero, clienteId, tagOperador, tagTipo, tagCategoria, tagStatus,
                tagHoraActualizacion, tagFechaActualizacion, tagSaldo);
        tag.record(new TagCreatedDomainEvent(id.value(),numero.value(),clienteId.value()));
        return tag;
    }

    public TagId id() {
        return id;
    }

    public TagNumero numero() {
        return numero;
    }

    public TagClienteId clienteId() {
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
        Tag tag = (Tag) o;
        assert id != null;
        return id.equals(tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numero);
    }
}
