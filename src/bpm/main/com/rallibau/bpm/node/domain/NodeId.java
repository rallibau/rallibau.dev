package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.Identifier;

public final class NodeId extends Identifier {

    public NodeId(String value) {
        super(value);
    }

    public NodeId() {
    }

    public static NodeId create(String value) {
        return new NodeId(value);
    }
}
