package com.inetum.livetooling.lista.tag.application.create;

import com.inetum.livetooling.lista.tag.domain.*;
import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.event.EventBus;

@Service
public final class TagCreator {
    private final TagRepository repository;
    private final EventBus eventBus;

    public TagCreator(TagRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(TagId id, TagNumero numero, TagClienteId clienteId, TagOperador tagOperador,
                       TagTipo tagTipo, TagCategoria tagCategoria, TagStatus tagStatus,
                       TagHoraActualizacion tagHoraActualizacion, TagFechaActualizacion tagFechaActualizacion,
                       TagSaldo tagSaldo) {
        Tag tag = Tag.create(id,numero, clienteId, tagOperador, tagTipo, tagCategoria, tagStatus,
                tagHoraActualizacion, tagFechaActualizacion, tagSaldo);
        repository.save(tag);
        eventBus.publish(tag.pullDomainEvents());
    }
}
