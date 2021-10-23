package com.rallibau.bpm.process.infrastructure.persistence.inMemory;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.bpm.process.infrastructure.persistence.inMemory.ProcessRepositoryInMemoryImpl;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class ProcessRepositoryShould {


    protected ProcessRepositoryInMemoryImpl repository;

    @BeforeEach
    public void setup() {
        repository = new ProcessRepositoryInMemoryImpl();
    }

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

    @Test
    public void search_all_existing_nodes() {

        Process process1 = ProcessMother.random();
        Process process2 = ProcessMother.random();

        repository.save(process1);
        repository.save(process2);

        assertThat(Arrays.asList(process1, process2), containsInAnyOrder(repository.searchAll().toArray()));
    }

}
