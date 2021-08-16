package com.rallibau.apps.gestionSaldo.config;

import com.rallibau.shared.domain.bus.command.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GestionSaldoConfig {
    private final CommandBus bus;

    public GestionSaldoConfig(CommandBus bus) {
        this.bus = bus;
    }
}
