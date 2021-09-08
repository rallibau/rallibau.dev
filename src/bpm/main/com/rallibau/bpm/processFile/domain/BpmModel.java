package com.rallibau.bpm.processFile.domain;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.process.domain.Process;

import java.util.List;

public class BpmModel {
    private Process process;
    private final List<Node> nodes;
    private final List<Connection> connections;

    public BpmModel(List<Node> nodes, List<Connection> connections) {
        this.nodes = nodes;
        this.connections = connections;
    }

    public Process process() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }

    public List<Node> nodes() {
        return nodes;
    }

    public List<Connection> connections() {
        return connections;
    }
}
