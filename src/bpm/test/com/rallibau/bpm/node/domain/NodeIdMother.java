package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.UuidMother;

public class NodeIdMother {
    public static NodeId random() {
        return NodeIdMother.create(UuidMother.random());
    }

    private static NodeId create(String name) {
        return NodeId.create(name);
    }
}
