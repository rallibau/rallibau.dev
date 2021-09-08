package com.rallibau.bpm.connection.domain;

public class ConnectionMother {
    public static Connection create(ConnectionId id, ConnectionType connectionType, NodeIdOwner nodeIdOwner, NodeIdTarget nodeIdTarget) {
        return Connection.create(id, connectionType, nodeIdOwner, nodeIdTarget);
    }

    public static Connection random() {
        return Connection.create(ConnectionIdMother.random(), ConnectionTypeMother.random(), NodeIdOwnerMother.random(), NodeIdTargetMother.random());
    }
}
