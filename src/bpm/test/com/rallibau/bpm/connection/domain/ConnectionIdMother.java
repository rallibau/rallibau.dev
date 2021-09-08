package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.UuidMother;

public class ConnectionIdMother {
    public static ConnectionId random() {
        return ConnectionId.create(UuidMother.random());
    }
}
