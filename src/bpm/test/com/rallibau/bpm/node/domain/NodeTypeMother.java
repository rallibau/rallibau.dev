package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.WordMother;

public class NodeTypeMother {
    public static NodeType random() {
        return NodeType.create(WordMother.random());
    }

    private static NodeType create(String name) {
        return NodeType.create(name);
    }
}
