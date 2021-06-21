package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.WordMother;

public final class TagHoraActualizacionMother {
    public static TagHoraActualizacion create(String tagHoraActualizacion) {
        return new TagHoraActualizacion(tagHoraActualizacion);
    }
    public static  TagHoraActualizacion random(){
        return create(WordMother.random());
    }
}
