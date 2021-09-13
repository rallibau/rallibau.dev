package com.rallibau.bpm.node.application.find;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.node.domain.NodeNotExist;
import com.rallibau.bpm.node.domain.NodeRepository;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.shared.domain.Service;

import java.util.List;

@Service
public class NodeFinder {
    private final NodeRepository nodeRepository;

    public NodeFinder(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public Node find(String id) throws NodeNotExist {
        return nodeRepository.get(new NodeId(id)).orElseThrow(() -> new NodeNotExist(new ProcessId(id)));
    }

    public List<Node> find() {
        return nodeRepository.searchAll();
    }
}
