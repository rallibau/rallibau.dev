package com.rallibau.schedule.task.domain;

public final class TaskMother {
    public static Task create(TaskId id, TaskNodeId taskNodeId){
        return Task.create(id,taskNodeId);
    }

    public static Task random(){
        return create(TaskIdMother.random(),TaskNodeIdMother.random());
    }
}
