package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.StringValueObject;

public class ConnectionType extends StringValueObject {
    public ConnectionType(String value) {
        super(value);
    }

    public ConnectionType() {
        super("");
    }

    public static ConnectionType create(String name) {
        return new ConnectionType(name);
    }
}
