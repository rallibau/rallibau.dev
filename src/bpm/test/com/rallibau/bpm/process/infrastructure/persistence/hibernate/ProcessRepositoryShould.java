package com.rallibau.bpm.process.infrastructure.persistence.hibernate;

import com.rallibau.apps.bpm.BpmApplication;
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
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class ProcessRepositoryShould {

    @Autowired
    protected ProcessRepositoryImp repository;

    @Test
    public void save_a_test_dont_failure() {
        Process process = ProcessMother.random();
        repository.save(process);
    }

    @Test
    public void search_by_criteria() {
        Process process1 = ProcessMother.random();
        Process process2 = ProcessMother.random();

        repository.save(process1);
        repository.save(process2);

        Criteria criteria = new Criteria(new Filters(Collections.singletonList(Filter.create("name", "=", process1.name().value()))), Order.asc("name"));

        assertThat(Collections.singletonList(process1), containsInAnyOrder(repository.matching(criteria).toArray()));

    }


}