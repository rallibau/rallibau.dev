package com.inetum.livetooling.viaje.cruce.domain;

import com.inetum.livetooling.shared.domain.UuidMother;

public final class CruceIdMother {
    public static CruceId create(String value){
        return new CruceId(value);
    }

    public static CruceId random(){
        return create(UuidMother.random());
    }
}
