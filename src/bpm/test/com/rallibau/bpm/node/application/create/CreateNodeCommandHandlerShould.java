package com.rallibau.bpm.node.application.create;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeMother;
import com.rallibau.bpm.node.domain.NodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class CreateNodeCommandHandlerShould {

    private CreateNodeCommandHandler createNodeCommandHandler;
    private NodeRepository nodeRepository;


    @BeforeEach
    protected void setUp() {
        nodeRepository = mock(NodeRepository.class);
        createNodeCommandHandler = new CreateNodeCommandHandler(new NodeCreator(nodeRepository));
    }

    @Test
    public void create_valid_node() {
        Node node = NodeMother.random();
        createNodeCommandHandler.handle(new CreateNodeCommand(node.id().value(), node.name().value(), node.nodeType().value()));
        verify(nodeRepository, atLeastOnce()).save(node);
    }
}
