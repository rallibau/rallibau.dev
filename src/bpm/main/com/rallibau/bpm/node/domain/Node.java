package com.rallibau.bpm.node.domain;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.shared.domain.AggregateRoot;

import java.util.Objects;

public final class Node extends AggregateRoot {
    private final NodeId id;
    private final NodeName name;

    public Node() {
        this.id = null;
        this.name = null;
    }

    public NodeId id() {
        return id;
    }

    public NodeName name() {
        return name;
    }

    public Node(NodeId id, NodeName name) {
        this.id = id;
        this.name = name;
    }

    public static Node create(NodeId id, NodeName name) {
        return new Node(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return id.equals(node.id) &&
                name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
