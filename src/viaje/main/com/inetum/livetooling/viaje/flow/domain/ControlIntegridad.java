package com.inetum.livetooling.viaje.flow.domain;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import com.inetum.livetooling.shared.domain.bus.query.QueryBus;
import com.inetum.livetooling.viaje.cruce.application.create.CruceChangeStateCommand;
import com.inetum.livetooling.viaje.cruce.application.find.CruceFinder;
import com.inetum.livetooling.viaje.cruce.application.find.CrucesResponse;
import com.inetum.livetooling.viaje.cruce.application.find.FindCruceQuery;
import com.inetum.livetooling.viaje.cruce.domain.CruceEstadoEnum;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;
import com.inetum.livetooling.viaje.cruce.domain.rules.common.ErrorRegla;
import com.inetum.livetooling.viaje.cruce.domain.rules.common.ReglasInterface;
import com.inetum.livetooling.viaje.tag.application.find.TagViajeFinder;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@Service
public class ControlIntegridad {
    private final CommandBus commandBus;
    private final QueryBus queryBus;
    private final CruceFinder cruceFinder;
    private final TagViajeFinder tagViajeFinder;

    public ControlIntegridad(CommandBus commandBus, QueryBus queryBus, CruceFinder cruceFinder, TagViajeFinder tagViajeFinder) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
        this.cruceFinder = cruceFinder;
        this.tagViajeFinder = tagViajeFinder;
    }

    public void validarReglas(CruceId cruceId) {


        CrucesResponse response = queryBus.ask(new FindCruceQuery(cruceId.value()));
        if (!response.getCruces().isEmpty()) {
            for (CrucesResponse.CruceResponse cruceResponse : response.getCruces()) {
                try {
                    ejecutarReglas(cruceResponse.getId());
                    cambiarEstadoCruce(cruceResponse.getId(), CruceEstadoEnum.DESCUENTO);
                } catch (Exception  errorRegla) {
                    errorRegla.printStackTrace();
                    cambiarEstadoCruce(cruceResponse.getId(), CruceEstadoEnum.REVISION);
                }
            }
        }

    }


    private void ejecutarReglas(String cruceId) throws NoSuchMethodException, InvocationTargetException,
            IllegalAccessException, InstantiationException, ErrorRegla {
        Reflections reflections = new Reflections("com.inetum.livetooling.viaje.cruce.domain.rules");


        Set<Class<? extends ReglasInterface>> allReglas = reflections.getSubTypesOf(ReglasInterface.class);
        for (Class<? extends ReglasInterface> regla : allReglas) {
            regla.getConstructor(CruceFinder.class,TagViajeFinder.class).newInstance(cruceFinder,tagViajeFinder).ejecutarRegla(cruceId);
        }
    }

    private void cambiarEstadoCruce(String cruceId, CruceEstadoEnum estadoDestino) {
        commandBus.dispatch(new CruceChangeStateCommand(cruceId, estadoDestino.name()));
    }
}
