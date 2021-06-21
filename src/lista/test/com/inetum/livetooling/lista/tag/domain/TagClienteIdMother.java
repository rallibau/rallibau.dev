package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.UuidMother;

public class TagClienteIdMother {
    public static TagClienteId create(String idCliente) {
        return new TagClienteId(idCliente);
    }
    public static  TagClienteId random(){
        return create(UuidMother.random());
    }
}
