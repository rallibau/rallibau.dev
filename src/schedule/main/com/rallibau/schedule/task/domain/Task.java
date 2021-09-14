package com.rallibau.schedule.task.domain;

import com.rallibau.schedule.story.domain.StoryId;
import com.rallibau.shared.domain.AggregateRoot;

import java.util.Objects;

public class Task extends AggregateRoot {

    private final TaskId id;
    private final TaskNodeId taskNodeId;
    private final StoryId storyId;

    public Task(TaskId id, TaskNodeId taskNodeId, StoryId storyId) {
        this.id = id;
        this.taskNodeId = taskNodeId;
        this.storyId = storyId;
    }

    public Task() {
        this.storyId = null;
        this.id = null;
        this.taskNodeId = null;
    }

    public static Task create(TaskId id, TaskNodeId taskNodeId, StoryId storyId) {
        return new Task(id, taskNodeId, storyId);
    }

    public TaskId id() {
        return id;
    }

    public TaskNodeId taskNodeId() {
        return taskNodeId;
    }

    public StoryId storyId() {
        return storyId;
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
                taskNodeId.equals(task.taskNodeId)
                && storyId.equals(task.storyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskNodeId, storyId);
    }
}
