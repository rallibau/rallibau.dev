package com.inetum.livetooling.viaje.aclaracion.application.create;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandler;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;

@Service
public class CreateAclaracionCommandHandler implements CommandHandler<CreateAclaracionCommand> {
    private final AclaracionCreator aclaracionCreator;

    public CreateAclaracionCommandHandler(AclaracionCreator aclaracionCreator) {
        this.aclaracionCreator = aclaracionCreator;
    }


    @Override
    public void handle(CreateAclaracionCommand command) {
        aclaracionCreator.create(command.id(), new CruceId(command.cruceId()));
    }
}
