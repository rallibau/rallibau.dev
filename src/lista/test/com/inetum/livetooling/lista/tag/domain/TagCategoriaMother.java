package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.WordMother;

public final class TagCategoriaMother {
    public static TagCategoria create(String categoria) {
        return new TagCategoria(categoria);
    }
    public static  TagCategoria random(){
        return create(WordMother.randomNumber());
    }
}
