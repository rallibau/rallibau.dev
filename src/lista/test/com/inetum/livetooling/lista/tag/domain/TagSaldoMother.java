package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.WordMother;

public final class TagSaldoMother {
    public static TagSaldo create(String tagSaldo) {
        return new TagSaldo(tagSaldo);
    }
    public static  TagSaldo random(){
        return create(WordMother.random());
    }
}
