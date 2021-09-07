package com.rallibau.schedule.task.domain;

import com.rallibau.shared.domain.Identifier;

public class TaskId extends Identifier {
    public TaskId(String value) {
        super(value);
    }

    public TaskId() {

    }

    public static TaskId create(String value) {
        return new TaskId(value);
    }
}
