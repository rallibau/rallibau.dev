package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.IntegerMother;

public final class TagOperadorMother {
    public static TagOperador create(Integer tagOperator) {
        return new TagOperador(tagOperator);
    }
    public static  TagOperador random(){
        return create(IntegerMother.random());
    }
}
