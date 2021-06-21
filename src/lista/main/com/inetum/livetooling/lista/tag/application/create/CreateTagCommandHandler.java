package com.inetum.livetooling.lista.tag.application.create;

import com.inetum.livetooling.lista.tag.domain.*;
import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.bus.command.CommandHandler;

@Service
public final class CreateTagCommandHandler implements CommandHandler<CreateTagCommand> {

    private final TagCreator tagCreator;

    public CreateTagCommandHandler(TagCreator tagCreator) {
        this.tagCreator = tagCreator;
    }

    @Override
    public void handle(CreateTagCommand command) {
        TagId id = new TagId(command.id());
        TagNumero tagNumero = new TagNumero(command.numero());
        TagClienteId clienteId = new TagClienteId(command.clienteId());
        TagOperador tagOperador = new TagOperador(command.tagOperador());
        TagTipo tagTipo = new TagTipo(command.tagTipo());
        TagCategoria tagCategoria = new TagCategoria(command.tagCategoria());
        TagStatus tagStatus = new TagStatus(command.tagStatus());
        TagHoraActualizacion tagHoraActualizacion = new TagHoraActualizacion(command.tagHoraActualizacion());
        TagFechaActualizacion tagFechaActualizacion = new TagFechaActualizacion(command.tagFechaActualizacon());
        TagSaldo tagSaldo = new TagSaldo(command.tagSaldo());

        tagCreator.create(id,tagNumero, clienteId, tagOperador, tagTipo, tagCategoria, tagStatus,
                tagHoraActualizacion, tagFechaActualizacion, tagSaldo);

    }
}
