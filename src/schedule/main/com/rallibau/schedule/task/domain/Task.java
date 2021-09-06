package com.rallibau.schedule.task.domain;

public class Task {

    private final TaskId taskId;
    private final TaskNodeId taskNodeId;

    public Task(TaskId taskId, TaskNodeId taskNodeId) {
        this.taskId = taskId;
        this.taskNodeId = taskNodeId;
    }

    public static Task create(TaskId taskId, TaskNodeId taskNodeId) {
        return new Task(taskId,taskNodeId);
    }
}
