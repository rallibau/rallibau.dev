package com.inetum.livetooling.viaje.aclaracion.application.update;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandler;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;

@Service
public class UpdateAclaracionCommandHandler implements CommandHandler<UpdateAclaracionCommand> {
    private final AclaracionUpdater uclaracionUpdater;

    public UpdateAclaracionCommandHandler(AclaracionUpdater uclaracionUpdater) {
        this.uclaracionUpdater = uclaracionUpdater;
    }


    @Override
    public void handle(UpdateAclaracionCommand command) {
        uclaracionUpdater.update(command.id(), new CruceId(command.cruceId()),command.aceptar());
    }
}
