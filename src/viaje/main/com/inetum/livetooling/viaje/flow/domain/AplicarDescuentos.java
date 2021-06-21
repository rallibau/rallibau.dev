package com.inetum.livetooling.viaje.flow.domain;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.viaje.cruce.application.create.CruceChangeStateCommand;
import com.inetum.livetooling.viaje.cruce.domain.CruceEstadoEnum;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;

@Service
public class AplicarDescuentos {
    private final CommandBus commandBus;

    public AplicarDescuentos(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    public void ejecutarDescuentos(CruceId cruceId) {
        //TODO: Aplicar descuentos


        commandBus.dispatch(new CruceChangeStateCommand(cruceId.value(), CruceEstadoEnum.PENDIENTE_COBRO.name()));
    }
}
