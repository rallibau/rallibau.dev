package com.rallibau.bpm.node.infrastructure.persistence;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.node.domain.*;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class NodeRespositoryShould {

    @Autowired
    private NodeRepository repository;

    @Test
    public void create_a_valid_node(){
        repository.save(NodeMother.random());
    }

    @Test
    public void search_all_existing_nodes(){

        Node node1 = NodeMother.random();
        Node node2 = NodeMother.random();

        repository.save(node1);
        repository.save(node2);

        assertThat(Arrays.asList(node1,node2),containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria(){
        Node node1 = NodeMother.create(NodeIdMother.random(), NodeName.create("Un nombre cualquiera"));
        repository.save(node1);

        Criteria criteria = new Criteria(new Filters(Arrays.asList(Filter.create("name","contains","mon"))), Order.asc("name"));

        assertThat(Arrays.asList(node1),containsInAnyOrder(repository.matching(criteria).toArray()));

    }
}
