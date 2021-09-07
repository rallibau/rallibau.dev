package com.rallibau.schedule.task.infrastructure.persistence;

import com.rallibau.schedule.task.domain.Task;
import com.rallibau.schedule.task.domain.TaskMother;
import com.rallibau.schedule.task.domain.TaskNodeIdMother;
import com.rallibau.schedule.task.domain.TaskRepository;
import com.rallibau.schedule.task.infrastructure.persistence.inMemory.TaskRepositoryInMemoryImpl;
import com.rallibau.shared.domain.criteria.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class TaskRepositoryShould {


    private TaskRepository taskRepository;


    @BeforeEach
    public void prepare() {
        taskRepository = new TaskRepositoryInMemoryImpl();
    }


    @Test
    public void get_all_existing_task() {
        Task task1 = TaskMother.random();
        Task task2 = TaskMother.random();
        taskRepository.save(task1);
        taskRepository.save(task2);
        assertThat(Arrays.asList(task1, task2), containsInAnyOrder(taskRepository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria() {
        Task task = TaskMother.random();
        taskRepository.save(task);
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("taskNodeId",
                                        FilterOperator.CONTAINS.value(),
                                        task.taskNodeId().value()))),
                Order.asc("taskNodeId"));
        assertThat(Arrays.asList(task), containsInAnyOrder(taskRepository.matching(criteria).toArray()));

    }

    @Test
    public void find_empty_result() {
        Task task1 = TaskMother.random();
        Task task2 = TaskMother.random();
        taskRepository.save(task1);
        taskRepository.save(task2);
        Criteria criteria = new Criteria(
                new Filters(
                        Arrays.asList(
                                Filter.create("taskNodeId",
                                        FilterOperator.CONTAINS.value(),
                                        TaskNodeIdMother.random().value()))),
                Order.asc("taskNodeId"));
        List<Task> task = taskRepository.matching(criteria);
        assertThat("Debemos obtener una lista vacia", task.size() == 0);

    }
}
