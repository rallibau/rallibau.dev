package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.WordMother;

public class NodeTypeMother {
    public static NodeType random() {
        return NodeType.create(NodeType.NODE_TYPE.random());
    }

    private static NodeType create(String name) {
        return NodeType.create(NodeType.NODE_TYPE.valueOf(name));
    }
}
