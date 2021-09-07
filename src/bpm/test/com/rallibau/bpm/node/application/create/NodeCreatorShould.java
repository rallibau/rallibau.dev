package com.rallibau.bpm.node.application.create;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeMother;
import com.rallibau.bpm.node.domain.NodeRepository;
import com.rallibau.bpm.node.infrastructure.persistence.inMemory.NodeRepositoryInMemoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeCreatorShould {

    private NodeCreator nodeCreator;
    private NodeRepository repository;

    @BeforeEach
    protected void setUp() {
        repository = new NodeRepositoryInMemoryImpl();
        nodeCreator = new NodeCreator(repository);
    }

    @Test
    public void node_creator_dont_failure() {
        Node node = NodeMother.random();
        nodeCreator.create(node);
    }
}
