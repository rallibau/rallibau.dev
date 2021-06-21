package com.inetum.livetooling.viaje.aclaracion.application.create;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.viaje.aclaracion.domain.*;
import com.inetum.livetooling.viaje.cruce.application.create.CruceChangeStateCommand;
import com.inetum.livetooling.viaje.cruce.domain.CruceEstadoEnum;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;

@Service
public class AclaracionCreator {
    private final CommandBus commandBus;
    private final AclaracionRepository aclaracionRepository;

    public AclaracionCreator(CommandBus commandBus,
                             AclaracionRepository aclaracionRepository) {
        this.commandBus = commandBus;
        this.aclaracionRepository = aclaracionRepository;
    }

    public void create(String idAclaracion, CruceId cruceId) {
        Aclaracion aclaracion = Aclaracion.create(new AclaracionId(idAclaracion),
                cruceId.value(),
                new AclaracionEstado(AclaracionEstadoEnum.ABIERTA));
        aclaracionRepository.save(aclaracion);

        commandBus.dispatch(new CruceChangeStateCommand(cruceId.value(),
                CruceEstadoEnum.REVISION_ACLARACION.name()));


    }
}
