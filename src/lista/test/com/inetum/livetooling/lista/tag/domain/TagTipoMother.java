package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.IntegerMother;

public final class TagTipoMother {
    public static TagTipo create(Integer tipo) {
        return new TagTipo(tipo);
    }
    public static  TagTipo random(){
        return create(IntegerMother.random());
    }
}
