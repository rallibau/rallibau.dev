package com.inetum.livetooling.viaje.cruce.application.create;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandler;
import com.inetum.livetooling.shared.domain.bus.event.EventBus;
import com.inetum.livetooling.shared.domain.events.viaje.CruceChangeStateDomainEvent;
import com.inetum.livetooling.viaje.cruce.domain.*;

import java.util.Optional;

@Service
public final class CruceChangeStateCommandHandler implements CommandHandler<CruceChangeStateCommand> {

    private final CruceRepository cruceRepository;
    private final EventBus eventBus;

    public CruceChangeStateCommandHandler(CruceRepository cruceRepository,
                                          EventBus eventBus) {
        this.cruceRepository = cruceRepository;
        this.eventBus = eventBus;
    }

    @Override
    public void handle(CruceChangeStateCommand command) {
        CruceId id = new CruceId(command.id());

        Optional<Cruce> cruce = cruceRepository.get(id);
        if (cruce.isPresent()) {
            cruce.get().setEstado(new CruceEstado(CruceEstadoEnum.valueOf(command.estado())));
            cruceRepository.save(cruce.get());
            CruceChangeStateDomainEvent cruceChangeStateDomainEvent = new CruceChangeStateDomainEvent(command.id());
            eventBus.publish(cruceChangeStateDomainEvent);
        }


    }
}
