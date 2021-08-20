package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.WordMother;

public class NodeMameMother {
    public static NodeName random() {
        return NodeMameMother.create(WordMother.random());
    }

    private static NodeName create(String name) {
        return NodeName.create(name);
    }
}
