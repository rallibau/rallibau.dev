package com.rallibau.schedule.task.application;

import com.rallibau.schedule.task.domain.Task;
import com.rallibau.schedule.task.domain.TaskRepository;
import com.rallibau.shared.domain.Service;

@Service
public class TaskCreator {
    private final TaskRepository taskRepository;

    public TaskCreator(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void create(Task task) {
        taskRepository.save(task);
    }
}
