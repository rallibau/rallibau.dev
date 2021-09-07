package com.rallibau.bpm.node.infrastructure.persistence;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeMother;
import com.rallibau.bpm.node.domain.NodeRepository;
import com.rallibau.bpm.node.infrastructure.persistence.inMemory.NodeRepositoryInMemoryImpl;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class NodeRespositoryShould {

    private NodeRepository repository;

    @BeforeEach
    public void prepare() {
        repository = new NodeRepositoryInMemoryImpl();
    }


    @Test
    protected void save_a_task() {
        repository.save(NodeMother.random());

    }


    @Test
    public void search_all_existing_nodes() {
        Node node1 = NodeMother.random();
        repository.save(node1);
        Node node2 = NodeMother.random();
        repository.save(node2);
        repository.searchAll().forEach(xx -> System.out.println(xx.name().value()));
        assertThat(Arrays.asList(node1, node2), containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria() {
        Node node = NodeMother.random();
        repository.save(node);
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("name",
                                        "contains",
                                        node.name().value()))),
                Order.asc("name"));
        assertThat(Arrays.asList(node), containsInAnyOrder(repository.matching(criteria).toArray()));

    }
}
