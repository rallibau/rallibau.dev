package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.IntegerMother;

public final class TagStatusMother {
    public static TagStatus create(Integer status) {
        return new TagStatus(status);
    }
    public static  TagStatus random(){
        return create(IntegerMother.random());
    }
}
