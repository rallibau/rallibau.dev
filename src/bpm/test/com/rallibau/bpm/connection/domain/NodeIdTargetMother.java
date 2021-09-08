package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.UuidMother;

public class NodeIdTargetMother {
    public static NodeIdTarget random() {
        return NodeIdTarget.create(UuidMother.random());
    }
}
