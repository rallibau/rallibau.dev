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
public class NodeCreatorShould {

    private NodeCreator nodeCreator;
    private NodeRepository repository;

    @BeforeEach
    protected void setUp() {
        repository = mock(NodeRepository.class);
        nodeCreator = new NodeCreator(repository);
    }

    @Test
    public void node_creator_dont_failure(){
        Node node = NodeMother.random();
        nodeCreator.create(node);
    }
}
