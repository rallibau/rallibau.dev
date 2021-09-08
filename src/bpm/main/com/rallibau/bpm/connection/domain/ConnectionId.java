package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.Identifier;

public class ConnectionId extends Identifier {

    public ConnectionId(String value) {
        super(value);
    }

    public ConnectionId() {
    }

    public static ConnectionId create(String value) {
        return new ConnectionId(value);
    }
}
