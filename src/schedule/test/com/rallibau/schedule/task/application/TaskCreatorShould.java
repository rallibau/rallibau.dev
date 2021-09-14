package com.rallibau.schedule.task.application;

import com.rallibau.schedule.task.application.create.TaskCreator;
import com.rallibau.schedule.task.domain.Task;
import com.rallibau.schedule.task.domain.TaskMother;
import com.rallibau.schedule.task.domain.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class TaskCreatorShould {

    private TaskCreator taskCreator;
    private TaskRepository taskRepository;

    @BeforeEach
    private void setUp() {
        this.taskRepository = mock(TaskRepository.class);
        this.taskCreator = new TaskCreator(taskRepository);

    }

    @Test
    public void create_scheduler_do_not_failure() {
        Task task = TaskMother.random();
        taskCreator.create(task);
        verify(taskRepository, atLeastOnce()).save(task);
    }

}
