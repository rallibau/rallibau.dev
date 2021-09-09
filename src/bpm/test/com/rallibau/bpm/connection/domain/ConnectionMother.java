package com.rallibau.bpm.connection.domain;

public class ConnectionMother {
    public static Connection create(ConnectionId id, NodeIdOwner nodeIdOwner, NodeIdTarget nodeIdTarget) {
        return Connection.create(id, nodeIdOwner, nodeIdTarget);
    }

    public static Connection random() {
        return Connection.create(ConnectionIdMother.random(), NodeIdOwnerMother.random(), NodeIdTargetMother.random());
    }
}
