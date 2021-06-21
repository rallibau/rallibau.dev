package com.inetum.livetooling.lista.tag.domain;

public final class TagMother {
    public static Tag create(TagId id, TagNumero numero, TagClienteId clienteId, TagOperador tagOperador,
                             TagTipo tagTipo, TagCategoria tagCategoria, TagStatus tagStatus,
                             TagHoraActualizacion tagHoraActualizacion, TagFechaActualizacion tagFechaActualizacon,
                             TagSaldo tagSaldo) {
        return Tag.create(id,numero, clienteId, tagOperador, tagTipo, tagCategoria, tagStatus,
                tagHoraActualizacion, tagFechaActualizacon, tagSaldo);
    }

   /* public static Tag fromRequest(CreateCruceCommand command) {
        return create(TagIdMother.create(command.id()),
                TagNumeroMother.create(command.ruta()));
    }*/

    public static Tag random() {
        return create(TagIdMother.random(),
                TagNumeroMother.random(),
                TagClienteIdMother.random(),
                TagOperadorMother.random(),
                TagTipoMother.random(),
                TagCategoriaMother.random(),
                TagStatusMother.random(),
                TagHoraActualizacionMother.random(),
                TagFechaActualizacionMother.random(),
                TagSaldoMother.random()
                );
    }
}
