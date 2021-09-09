package com.rallibau.bpm.node.application.find;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeRepository;
import com.rallibau.shared.domain.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NodeFinder {
    private final NodeRepository nodeRepository;

    public NodeFinder(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Optional<Node> find(String id) {
        return nodeRepository.get(new NodeId(id));
    }

    public List<Node> find() {
        return nodeRepository.searchAll();
    }
}
