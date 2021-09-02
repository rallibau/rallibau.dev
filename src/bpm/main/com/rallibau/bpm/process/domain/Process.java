package com.rallibau.bpm.process.domain;

import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.events.bpm.ProcessCreatedDomainEvent;

import java.util.List;
import java.util.Objects;

public final class Process extends AggregateRoot {
    private ProcessId id;
    private ProcessName name;
    private List<NodeId> nodes;

    public Process(ProcessId id, ProcessName name, List<NodeId> nodes) {
        this.id = id;
        this.name = name;
        this.nodes = nodes;
    }

    public Process() {
        this.nodes = null;
        this.id = null;
        this.name = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Process node = (Process) o;
        return id.equals(node.id) &&
                name.equals(node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static Process create(ProcessId id, ProcessName name, List<NodeId> nodes) {
        Process process = new Process(id, name, nodes);
        process.record(new ProcessCreatedDomainEvent(id.value(), name.value()));
        return process;
    }

    public ProcessId id() {
        return id;
    }

    public ProcessName name() {
        return name;
    }

    public List<NodeId> nodes() {
        return nodes;
    }

    public void addNode(NodeId nodeId) {
        nodes.add(nodeId);
    }
}
