package com.rallibau.schedule.task.domain;

import com.rallibau.shared.domain.UuidMother;

public class TaskIdMother {
    public static TaskId random() {
        return TaskIdMother.create(UuidMother.random());
    }

    public static TaskId create(String value) {
        return TaskId.create(value);
    }
}
