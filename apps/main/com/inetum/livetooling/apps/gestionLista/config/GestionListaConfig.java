package com.inetum.livetooling.apps.gestionLista.config;

import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GestionListaConfig {
    private final CommandBus bus;

    public GestionListaConfig(CommandBus bus) {
        this.bus = bus;
    }
}
