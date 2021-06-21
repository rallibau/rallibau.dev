package com.inetum.livetooling.saldo.application.recargasManuales;

import com.inetum.livetooling.saldo.recarga.domain.RecargaId;
import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateRecargaCommandHandler implements CommandHandler<CreateRecargaCommand> {

    private final InsercionRecarga insercionRecarga;

    public CreateRecargaCommandHandler(InsercionRecarga insercionRecarga) {
        this.insercionRecarga = insercionRecarga;
    }

    @Override
    public void handle(CreateRecargaCommand command) {
        RecargaId id = new RecargaId(command.id());
        insercionRecarga.create(id);

    }
}
