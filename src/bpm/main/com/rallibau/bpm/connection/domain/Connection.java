package com.rallibau.bpm.connection.domain;

import com.rallibau.shared.domain.AggregateRoot;

import java.util.Objects;

public class Connection extends AggregateRoot {

    private final ConnectionId id;
    private final NodeIdOwner nodeIdOwner;
    private final NodeIdTarget nodeIdTarget;

    public Connection(ConnectionId id, NodeIdOwner nodeIdOwner, NodeIdTarget nodeIdTarget) {
        this.id = id;
        this.nodeIdOwner = nodeIdOwner;
        this.nodeIdTarget = nodeIdTarget;
    }

    public Connection() {
        this.id = null;
        this.nodeIdOwner = null;
        this.nodeIdTarget = null;
    }


    public static Connection create(ConnectionId id, NodeIdOwner nodeIdOwner, NodeIdTarget nodeIdTarget) {
        return new Connection(id, nodeIdOwner, nodeIdTarget);
    }

    public ConnectionId id() {
        return id;
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
        return id.equals(connection.id) && nodeIdOwner.equals(connection.nodeIdOwner) && nodeIdTarget.equals(connection.nodeIdTarget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nodeIdOwner, nodeIdOwner());
    }
}
