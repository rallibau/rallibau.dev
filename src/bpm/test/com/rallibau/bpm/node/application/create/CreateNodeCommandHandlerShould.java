package com.rallibau.bpm.node.application.create;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeMother;
import com.rallibau.bpm.node.domain.NodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class CreateNodeCommandHandlerShould {

    private CreateNodeCommandHandler createNodeCommandHandler;
    private NodeRepository nodeRepository;
    private NodeCreator nodeCreator;

    @BeforeEach
    protected void setUp() {
        nodeRepository = mock(NodeRepository.class);
        createNodeCommandHandler = new CreateNodeCommandHandler(new NodeCreator(nodeRepository));
    }

    @Test
    public void create_valid_node() {
        Node node = NodeMother.random();
        createNodeCommandHandler.handle(new CreateNodeCommand(node.id().value(), node.name().value()));
        verify(nodeRepository, atLeastOnce()).save(node);
    }
}
