package com.rallibau.schedule.task.domain;

import com.rallibau.schedule.story.domain.StoryId;
import com.rallibau.schedule.story.domain.StoryIdMother;

public final class TaskMother {
    public static Task create(TaskId id, TaskNodeId taskNodeId, StoryId storyId){
        return Task.create(id,taskNodeId, storyId);
    }

    public static Task random(){
        return create(TaskIdMother.random(),TaskNodeIdMother.random(), StoryIdMother.random());
    }
}
