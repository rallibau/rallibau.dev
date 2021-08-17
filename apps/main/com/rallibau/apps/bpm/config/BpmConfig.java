package com.rallibau.apps.bpm.config;

import com.rallibau.shared.domain.bus.command.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BpmConfig {
    private final CommandBus bus;

    public BpmConfig(CommandBus bus) {
        this.bus = bus;
    }
}
