package com.rallibau.bpm.node.domain;

import com.rallibau.shared.domain.AggregateRoot;

import java.util.Objects;

public final class Node extends AggregateRoot {

    private final NodeId id;
    private final NodeName name;
    private final NodeType nodeType;

    public Node() {
        this.id = null;
        this.name = null;
        this.nodeType = null;
    }

    public NodeId id() {
        return id;
    }

    public NodeName name() {
        return name;
    }

    public NodeType nodeType() {
        return nodeType;
    }

    public Node(NodeId id, NodeName name, NodeType nodeType) {
        this.id = id;
        this.name = name;
        this.nodeType = nodeType;
    }

    public static Node create(NodeId id, NodeName name, NodeType nodeType) {
        return new Node(id, name, nodeType);
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
                name.equals(node.name) && nodeType.equals(node.nodeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, nodeType);
    }
}
