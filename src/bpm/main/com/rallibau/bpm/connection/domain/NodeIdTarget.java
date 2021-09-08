package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.Identifier;

public class NodeIdTarget extends Identifier {
    public NodeIdTarget(String value) {
        super(value);
    }

    public NodeIdTarget() {

    }

    public static NodeIdTarget create(String value) {
        return new NodeIdTarget(value);
    }
}
