package com.inetum.livetooling.viaje.cruce.application.create;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.event.EventBus;
import com.inetum.livetooling.viaje.cruce.domain.*;

@Service
public final class CruceCreator {
    private final CruceRepository repository;
    private final EventBus eventBus;

    public CruceCreator(CruceRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(CruceId id, CruceRuta ruta,String tagNumero) {
        if(repository.get(id).isPresent()){
            return;
        }
        Cruce cruce = Cruce.create(id,ruta,new CruceEstado(CruceEstadoEnum.PENDIENTE),tagNumero);
        persist(cruce);
    }

    public void persist(Cruce cruce){
        if(!validar(cruce)){
            cruce.setEstado(new CruceEstado(CruceEstadoEnum.RECHAZADO));
        }
        repository.save(cruce);
        eventBus.publish(cruce.pullDomainEvents());
    }

    private boolean validar(Cruce cruce){
        //TODO: validaciones
        return true;
    }
}
