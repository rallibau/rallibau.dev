package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.Identifier;

public final class NodeId extends Identifier {

    public NodeId(String id) {
        super(id);
    }

    public NodeId() {
    }

    public static NodeId create(String id) {
        return new NodeId(id);
    }
}
