package com.inetum.livetooling.viaje.cruce.application.create;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.event.EventBus;
import com.inetum.livetooling.shared.domain.events.viaje.CruceChangeStateDomainEvent;
import com.inetum.livetooling.viaje.cruce.domain.Cruce;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;
import com.inetum.livetooling.viaje.cruce.domain.CruceRepository;
import com.inetum.livetooling.viaje.cruce.domain.CruceRuta;

import java.util.Optional;

@Service
public final class CruceUpdater {
    private final CruceRepository repository;
    private final EventBus eventBus;

    public CruceUpdater(CruceRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(CruceId id, Optional<CruceRuta> ruta, Optional<String> tagNumero) {
        Optional<Cruce> cruce = repository.get(id);
        if (cruce.isPresent()) {
            tagNumero.ifPresent(s -> cruce.get().setTagNumero(s));
            ruta.ifPresent(cruceRuta -> cruce.get().setRuta(cruceRuta));
            persist(cruce.get());
        }

    }

    public void persist(Cruce cruce) {
        repository.save(cruce);
        CruceChangeStateDomainEvent cruceChangeStateDomainEvent = new CruceChangeStateDomainEvent(cruce.id().value());
        eventBus.publish(cruceChangeStateDomainEvent);
        eventBus.publish(cruce.pullDomainEvents());
    }
}
