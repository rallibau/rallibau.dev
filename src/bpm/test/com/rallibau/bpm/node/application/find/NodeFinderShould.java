package com.rallibau.bpm.node.application.find;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeMother;
import com.rallibau.bpm.node.domain.NodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class NodeFinderShould {

    private NodeFinder nodeFinder;
    private NodeRepository nodeRepository;


    @BeforeEach
    private void setUp() {
        nodeRepository = mock(NodeRepository.class);
        nodeFinder = new NodeFinder(nodeRepository);
    }


    @Test
    public void find_a_existing_node() {
        Node node = NodeMother.random();
        when(nodeRepository.get(node.id())).thenReturn(Optional.of(node));
        Optional<Node> nodeResponse = nodeFinder.find(node.id().value());
        assertThat("obtnemos un nodo", nodeResponse.isPresent());
    }

}
