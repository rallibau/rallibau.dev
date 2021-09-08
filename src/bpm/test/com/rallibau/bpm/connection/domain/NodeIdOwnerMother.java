package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.UuidMother;

public class NodeIdOwnerMother {
    public static NodeIdOwner random() {
        return NodeIdOwner.create(UuidMother.random());
    }
}
