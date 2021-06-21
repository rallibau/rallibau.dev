package com.inetum.livetooling.viaje.cruce.domain;

import com.inetum.livetooling.shared.domain.WordMother;

public final class CruceRutaMother {
    public static CruceRuta create(String ruta) {
        return new CruceRuta(ruta);
    }
    public static  CruceRuta random(){
        return create(WordMother.random());
    }
}
