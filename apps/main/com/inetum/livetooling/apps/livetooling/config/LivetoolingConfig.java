package com.inetum.livetooling.apps.livetooling.config;

import com.inetum.livetooling.shared.domain.bus.command.CommandBus;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LivetoolingConfig {
    private final CommandBus bus;

    public LivetoolingConfig(CommandBus bus) {
        this.bus = bus;
    }
}
