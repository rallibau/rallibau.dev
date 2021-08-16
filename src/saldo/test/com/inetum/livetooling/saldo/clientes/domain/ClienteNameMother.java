package com.inetum.livetooling.saldo.clientes.domain;

import com.rallibau.shared.domain.WordMother;

public final class ClienteNameMother {
    public static ClienteName create(String name) {
        return new ClienteName(name);
    }
    public static  ClienteName random(){
           return create(WordMother.random());
    }
}
