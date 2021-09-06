package com.rallibau.schedule.task.domain;

import com.rallibau.shared.domain.UuidMother;

public class TaskNodeIdMother {
    public static TaskNodeId random() {
        return TaskNodeIdMother.create(UuidMother.random());
    }
    public static TaskNodeId create(String value) {
        return TaskNodeId.create(value);
    }
}
