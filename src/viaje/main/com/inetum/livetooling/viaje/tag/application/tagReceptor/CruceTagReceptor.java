package com.inetum.livetooling.viaje.tag.application.tagReceptor;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.event.DomainEventSubscriber;
import com.inetum.livetooling.shared.domain.events.lista.TagCreatedDomainEvent;
import com.inetum.livetooling.viaje.tag.domain.TagViajesNumero;
import com.inetum.livetooling.viaje.tag.domain.TagViajes;
import com.inetum.livetooling.viaje.tag.domain.TagViajesId;
import com.inetum.livetooling.viaje.tag.domain.TagViajesRepository;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TagCreatedDomainEvent.class})
public class CruceTagReceptor {
    private final TagViajesRepository tagViajesRepository;

    public CruceTagReceptor(TagViajesRepository tagViajesRepository) {
        this.tagViajesRepository = tagViajesRepository;
    }


    @EventListener
    public void on(TagCreatedDomainEvent event) {
        tagViajesRepository.save(new TagViajes(new TagViajesId(event.aggregateId()), new TagViajesNumero(event.numero()), event.clienteId()));
    }
}
