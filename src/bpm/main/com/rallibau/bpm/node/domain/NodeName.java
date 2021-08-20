package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.StringValueObject;

public final class NodeName extends StringValueObject {
    public NodeName(String value) {
        super(value);
    }
    public NodeName() {
        super("");
    }

    public static NodeName create(String name) {
        return new NodeName(name);
    }
}
