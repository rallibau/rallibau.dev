package com.rallibau.bpm.process.infrastructure.persistence;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeIdMother;
import com.rallibau.bpm.node.domain.NodeMother;
import com.rallibau.bpm.node.domain.NodeName;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
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
public class ProcessRepositoryShould {

    @Autowired
    protected ProcessRepository repository;

    @Test
    public void save_a_test_dont_failure(){
        Process process = ProcessMother.random();
        repository.save(process);
    }

    @Test
    public void search_all_existing_nodes(){

        Process process1 = ProcessMother.random();
        Process process2 = ProcessMother.random();

        repository.save(process1);
        repository.save(process2);

       // assertThat(Arrays.asList(process1,process2),containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria(){
        Process process1 = ProcessMother.random();
        Process process2 = ProcessMother.random();

        repository.save(process1);
        repository.save(process2);

        Criteria criteria = new Criteria(new Filters(Arrays.asList(Filter.create("name","=",process1.name().value()))), Order.asc("name"));

        assertThat(Arrays.asList(process1),containsInAnyOrder(repository.matching(criteria).toArray()));

    }

}
