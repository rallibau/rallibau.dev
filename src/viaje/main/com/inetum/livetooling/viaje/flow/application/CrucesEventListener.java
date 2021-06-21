package com.inetum.livetooling.viaje.flow.application;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.event.DomainEventSubscriber;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.shared.domain.events.viaje.CruceChangeStateDomainEvent;
import com.inetum.livetooling.shared.domain.events.viaje.CruceCreatedDomainEvent;
import com.inetum.livetooling.viaje.cruce.application.find.CrucesResponse;
import com.inetum.livetooling.viaje.cruce.application.find.FindCruceQuery;
import com.inetum.livetooling.viaje.cruce.domain.CruceEstadoEnum;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;
import com.inetum.livetooling.viaje.flow.domain.AplicarDescuentos;
import com.inetum.livetooling.viaje.flow.domain.CobrarViaje;
import com.inetum.livetooling.viaje.flow.domain.ControlIntegridad;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({CruceCreatedDomainEvent.class, CruceChangeStateDomainEvent.class})
public class CrucesEventListener {
    private final QueryBus queryBus;
    private final ControlIntegridad controlIntegridad;
    private final AplicarDescuentos aplicarDescuentos;
    private final CobrarViaje cobrarViaje;

    public CrucesEventListener(QueryBus queryBus, ControlIntegridad controlIntegridad,
                               AplicarDescuentos aplicarDescuentos,
                               CobrarViaje cobrarViaje) {
        this.queryBus = queryBus;
        this.controlIntegridad = controlIntegridad;
        this.aplicarDescuentos = aplicarDescuentos;
        this.cobrarViaje = cobrarViaje;
    }


    @EventListener
    public void on(CruceCreatedDomainEvent event) {
        System.out.println("Creado !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        CrucesResponse response = queryBus.ask(new FindCruceQuery(event.aggregateId()));
        if (!response.getCruces().isEmpty()) {
            for (CrucesResponse.CruceResponse cruceResponse : response.getCruces()) {
                if(cruceResponse.getEstado().equals(CruceEstadoEnum.PENDIENTE.name())){
                    System.out.println("Validando !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    controlIntegridad.validarReglas(new CruceId(event.aggregateId()));
                }
            }
        }

    }

    @EventListener
    public void on(CruceChangeStateDomainEvent event) {
        CrucesResponse response = queryBus.ask(new FindCruceQuery(event.aggregateId()));
        if (!response.getCruces().isEmpty()) {
            for (CrucesResponse.CruceResponse cruceResponse : response.getCruces()) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! "+ cruceResponse.getEstado()+" "+response.getCruces().size());
                if(cruceResponse.getEstado().equals(CruceEstadoEnum.DESCUENTO.name())){
                    System.out.println("Descuentos !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    aplicarDescuentos.ejecutarDescuentos(new CruceId(event.aggregateId()));
                } else if(cruceResponse.getEstado().equals(CruceEstadoEnum.PENDIENTE_COBRO.name())){
                    System.out.println("A cobrar !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    cobrarViaje.cobrarViaje(new CruceId(event.aggregateId()));
                }else if(cruceResponse.getEstado().equals(CruceEstadoEnum.REVISION.name())){
                    System.out.println("A revisar !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    controlIntegridad.validarReglas(new CruceId(event.aggregateId()));
                }
            }
        }
    }

}
