package com.rallibau.bpm.connection.domain;

import java.util.Objects;

public class Connection {

    private final ConnectionId id;
    private final ConnectionType connectionType;
    private final NodeIdOwner nodeIdOwner;
    private final NodeIdTarget nodeIdTarget;

    public Connection(ConnectionId id, ConnectionType connectionType, NodeIdOwner nodeIdOwner, NodeIdTarget nodeIdTarget) {
        this.id = id;
        this.connectionType = connectionType;
        this.nodeIdOwner = nodeIdOwner;
        this.nodeIdTarget = nodeIdTarget;
    }

    public Connection() {
        this.id = null;
        this.connectionType = null;
        this.nodeIdOwner = null;
        this.nodeIdTarget = null;
    }


    public static Connection create(ConnectionId id, ConnectionType connectionType, NodeIdOwner nodeIdOwner, NodeIdTarget nodeIdTarget) {
        return new Connection(id, connectionType, nodeIdOwner, nodeIdTarget);
    }

    public ConnectionId id() {
        return id;
    }

    public ConnectionType connectionType() {
        return connectionType;
    }

    public NodeIdOwner nodeIdOwner() {
        return nodeIdOwner;
    }

    public NodeIdTarget nodeIdTarget() {
        return nodeIdTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Connection connection = (Connection) o;
        return id.equals(connection.id) &&
                connectionType.equals(connection.connectionType) && nodeIdOwner.equals(connection.nodeIdOwner) && nodeIdTarget.equals(connection.nodeIdTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, connectionType, nodeIdOwner, nodeIdOwner());
    }
}
