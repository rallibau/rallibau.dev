package com.inetum.livetooling.lista.tag.domain;

import com.inetum.livetooling.shared.domain.UuidMother;

public final class TagIdMother {
    public static TagId create(String value){
        return new TagId(value);
    }

    public static TagId random(){
        return create(UuidMother.random());
    }
}
