package com.rallibau.schedule.task.domain;

import com.rallibau.shared.domain.Identifier;

public class TaskNodeId extends Identifier {
    public TaskNodeId(String value) {
        super(value);
    }

    public TaskNodeId() {

    }

    public static TaskNodeId create(String value) {
            return new TaskNodeId(value);
    }
}
