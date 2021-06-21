package com.inetum.livetooling.lista.tag.application.create;

import com.inetum.livetooling.shared.domain.bus.command.Command;

public final class CreateTagCommand implements Command {
    private final String id;
    private final String numero;
    private final String clienteId;
    private final Integer tagOperador;
    private final Integer tagTipo;
    private final String tagCategoria;
    private final Integer tagStatus;
    private final String tagHoraActualizacion;
    private final String tagFechaActualizacon;
    private final String tagSaldo;


    public CreateTagCommand(String id,
                            String numero,
                            String clienteId,
                            Integer tagOperador,
                            Integer tagTipo,
                            String tagCategoria,
                            Integer tagStatus,
                            String tagHoraActualizacion,
                            String tagFechaActualizacon,
                            String tagSaldo) {
        this.id = id;
        this.numero = numero;
        this.clienteId = clienteId;
        this.tagOperador = tagOperador;
        this.tagTipo = tagTipo;
        this.tagCategoria = tagCategoria;
        this.tagStatus = tagStatus;
        this.tagHoraActualizacion = tagHoraActualizacion;
        this.tagFechaActualizacon = tagFechaActualizacon;
        this.tagSaldo = tagSaldo;
    }

    public String id() {
        return id;
    }

    public String numero() {
        return numero;
    }

    public String clienteId() {
        return clienteId;
    }

    public Integer tagOperador() {
        return tagOperador;
    }

    public Integer tagTipo() {
        return tagTipo;
    }

    public String tagCategoria() {
        return tagCategoria;
    }

    public Integer tagStatus() {
        return tagStatus;
    }

    public String tagHoraActualizacion() {
        return tagHoraActualizacion;
    }

    public String tagFechaActualizacon() {
        return tagFechaActualizacon;
    }

    public String tagSaldo() {
        return tagSaldo;
    }
}
