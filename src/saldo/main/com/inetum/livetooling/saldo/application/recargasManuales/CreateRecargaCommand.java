package com.inetum.livetooling.saldo.application.recargasManuales;

import com.inetum.livetooling.shared.domain.bus.command.Command;

public final class CreateRecargaCommand implements Command {
    private final String id;

    public CreateRecargaCommand(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

}
