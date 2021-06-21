package com.inetum.livetooling.viaje.cruce.domain.rules.common;

public interface ReglasInterface {
    public void ejecutarRegla(String cruceId) throws ErrorRegla;
}
