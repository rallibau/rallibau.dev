package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.WordMother;

public class ConnectionTypeMother {
    public static ConnectionType random() {
        return ConnectionType.create(WordMother.random());
    }
}
