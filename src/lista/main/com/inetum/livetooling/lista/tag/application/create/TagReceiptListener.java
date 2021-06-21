package com.inetum.livetooling.lista.tag.application.create;

import com.inetum.livetooling.lista.tag.domain.*;
import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.event.DomainEventSubscriber;
import com.inetum.livetooling.shared.domain.events.lista.TagReceivedDomainEvent;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TagReceivedDomainEvent.class})
public class TagReceiptListener {
    private final TagCreator tagCreator;

    public TagReceiptListener(TagCreator tagCreator) {
        this.tagCreator = tagCreator;
    }


    @EventListener
    public void on(TagReceivedDomainEvent event) {
        TagId id = new TagId(event.eventId());
        TagNumero tagNumero = new TagNumero(event.numero());
        TagClienteId clienteId = new TagClienteId(event.clienteId());
        TagOperador tagOperador = new TagOperador(event.tagOperador());
        TagTipo tagTipo = new TagTipo(event.tagTipo());
        TagCategoria tagCategoria = new TagCategoria(event.tagCategoria());
        TagStatus tagStatus = new TagStatus(event.tagStatus());
        TagHoraActualizacion tagHoraActualizacion = new TagHoraActualizacion(event.tagHoraActualizacion());
        TagFechaActualizacion tagFechaActualizacion = new TagFechaActualizacion(event.tagFechaActualizacon());
        TagSaldo tagSaldo = new TagSaldo(event.tagSaldo());

        tagCreator.create(id,tagNumero, clienteId, tagOperador, tagTipo, tagCategoria, tagStatus,
                tagHoraActualizacion, tagFechaActualizacion, tagSaldo);
    }
}
