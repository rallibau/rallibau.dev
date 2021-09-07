package com.rallibau.schedule.task.domain;

import com.rallibau.shared.domain.AggregateRoot;

import java.util.Objects;

public class Task extends AggregateRoot{

    private final TaskId id;
    private final TaskNodeId taskNodeId;

    public Task(TaskId id, TaskNodeId taskNodeId) {
        this.id = id;
        this.taskNodeId = taskNodeId;
    }

    public Task() {
        this.id = null;
        this.taskNodeId = null;
    }

    public static Task create(TaskId id, TaskNodeId taskNodeId) {
        return new Task(id,taskNodeId);
    }

    public TaskId id() {
        return id;
    }

    public TaskNodeId taskNodeId() {
        return taskNodeId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return id.equals(task.id) &&
                taskNodeId.equals(task.taskNodeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskNodeId);
    }
}
