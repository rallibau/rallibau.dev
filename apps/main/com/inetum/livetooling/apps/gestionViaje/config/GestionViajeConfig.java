package com.inetum.livetooling.apps.gestionViaje.config;

import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GestionViajeConfig {
    private final CommandBus bus;

    public GestionViajeConfig(CommandBus bus) {
        this.bus = bus;
    }
}
