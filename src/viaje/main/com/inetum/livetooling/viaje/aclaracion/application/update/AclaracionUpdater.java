package com.inetum.livetooling.viaje.aclaracion.application.update;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.viaje.aclaracion.domain.*;
import com.inetum.livetooling.viaje.cruce.application.create.CruceChangeStateCommand;
import com.inetum.livetooling.viaje.cruce.domain.*;

import java.util.Optional;

@Service
public class AclaracionUpdater {
    private final CommandBus commandBus;
    private final AclaracionRepository aclaracionRepository;

    public AclaracionUpdater(CommandBus commandBus, AclaracionRepository aclaracionRepository) {
        this.commandBus = commandBus;
        this.aclaracionRepository = aclaracionRepository;
    }

    public void update(String idAclaracion, CruceId cruceId, Boolean aceptar){
        Optional<Aclaracion> aclaracion = aclaracionRepository.get(new AclaracionId(idAclaracion));
        if(aclaracion.isPresent()) {
            aclaracion.get().setEstado(new AclaracionEstado(AclaracionEstadoEnum.CERRADA));
            aclaracionRepository.save(aclaracion.get());
            String estado = CruceEstadoEnum.NO_COBRABLE.name();
            if(aceptar){
                estado = CruceEstadoEnum.DESCUENTO.name();
            }
            commandBus.dispatch(new CruceChangeStateCommand(cruceId.value(),estado));
        }

    }
}
