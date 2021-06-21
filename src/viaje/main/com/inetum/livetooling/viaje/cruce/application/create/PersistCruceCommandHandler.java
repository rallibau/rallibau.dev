package com.inetum.livetooling.viaje.cruce.application.create;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandler;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;
import com.inetum.livetooling.viaje.cruce.domain.CruceRuta;

import java.util.Optional;

@Service
public final class PersistCruceCommandHandler implements CommandHandler<PersistCruceCommand> {

    private final CruceCreator cruceCreator;
    private final CruceUpdater cruceUpdater;

    public PersistCruceCommandHandler(CruceCreator cruceCreator, CruceUpdater cruceUpdater) {
        this.cruceCreator = cruceCreator;
        this.cruceUpdater = cruceUpdater;
    }

    @Override
    public void handle(PersistCruceCommand command) {
        CruceId id = new CruceId(command.id());
        Optional<CruceRuta> ruta = Optional.empty();
        if(command.ruta() != null && !command.ruta().isEmpty()) {
            ruta = Optional.of(new CruceRuta(command.ruta()));
        }

        Optional<String> tagNumero = Optional.empty();
        if(command.tagNumero() != null && !command.tagNumero().isEmpty()) {
            tagNumero = Optional.of(command.tagNumero());
        }


        if(command.update()) {
            cruceUpdater.create(id,ruta, tagNumero);
        }else {
            cruceCreator.create(id,new CruceRuta(command.ruta()), command.tagNumero());
        }


    }
}
