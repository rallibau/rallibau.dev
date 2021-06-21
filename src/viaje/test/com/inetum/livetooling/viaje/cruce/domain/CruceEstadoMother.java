package com.inetum.livetooling.viaje.cruce.domain;

import java.util.Random;

public final class CruceEstadoMother {
    public static CruceEstado create(CruceEstadoEnum estado) {
        return new CruceEstado(estado);
    }
    public static  CruceEstado random(){
        Random rand = new Random();
        int randomNum = rand.nextInt((CruceEstadoEnum.values().length));
        return create(CruceEstadoEnum.values()[randomNum]);
    }
}
