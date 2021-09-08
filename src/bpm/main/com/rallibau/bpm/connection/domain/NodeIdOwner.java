package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.Identifier;

public class NodeIdOwner extends Identifier {
    public NodeIdOwner(String value) {
        super(value);
    }

    public NodeIdOwner() {

    }

    public static NodeIdOwner create(String value) {
        return new NodeIdOwner(value);
    }
}
