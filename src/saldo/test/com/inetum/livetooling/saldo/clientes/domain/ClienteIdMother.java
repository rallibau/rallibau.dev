package com.inetum.livetooling.saldo.clientes.domain;

import com.rallibau.shared.domain.UuidMother;

public final class ClienteIdMother {
    public static ClienteId create(String value){
        return new ClienteId(value);
    }

    public static ClienteId random(){
        return create(UuidMother.random());
    }
}
