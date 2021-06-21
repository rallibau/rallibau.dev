package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.WordMother;

public final class TagNumeroMother {
    public static TagNumero create(String numero) {
        return new TagNumero(numero);
    }
    public static  TagNumero random(){
        return create(WordMother.randomNumber());
    }
}
