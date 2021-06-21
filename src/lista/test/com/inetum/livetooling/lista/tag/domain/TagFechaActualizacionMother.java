package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.WordMother;

public final class TagFechaActualizacionMother {
    public static TagFechaActualizacion create(String tagFechaActualizacion) {
        return new TagFechaActualizacion(tagFechaActualizacion);
    }
    public static  TagFechaActualizacion random(){
        return create(WordMother.random());
    }
}
