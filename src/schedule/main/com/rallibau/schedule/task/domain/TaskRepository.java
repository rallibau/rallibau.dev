package com.rallibau.schedule.task.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    void save(Task random);

    Optional<Task> get(TaskId id);


    List<Task> searchAll();

    List<Task> matching(Criteria criteria);
}
