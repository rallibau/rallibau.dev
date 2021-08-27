package com.rallibau.bpm.node.application.create;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeRepository;
import com.rallibau.shared.domain.Monitor;
import com.rallibau.shared.domain.Service;

@Service
public class NodeCreator {

    private final NodeRepository repository;


    public NodeCreator(NodeRepository repository) {
        this.repository = repository;
    }

    @Monitor
    public void create(Node node){
        repository.save(node);
    }
}
