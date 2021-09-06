package com.rallibau.schedule.task.infrastructure.persistence;

import com.rallibau.apps.schedule.ScheduleApplication;
import com.rallibau.schedule.task.domain.TaskMother;
import com.rallibau.schedule.task.domain.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = ScheduleApplication.class)
@SpringBootTest
public class TaskRepositoryShould {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void save_task_dont_failure(){
        taskRepository.save(TaskMother.random());
    }
}
