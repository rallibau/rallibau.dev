package com.rallibau.schedule.task.infrastructure.persistence.inMemory;

import com.rallibau.schedule.task.domain.Task;
import com.rallibau.schedule.task.domain.TaskId;
import com.rallibau.schedule.task.domain.TaskRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.List;
import java.util.Optional;

public class TaskRepositoryInMemoryImpl extends MemoryRepository<Task, TaskId> implements TaskRepository {

    public TaskRepositoryInMemoryImpl() {
        super(Task.class);
    }

    @Override
    public void save(Task task) {
        persist(task);
    }

    @Override
    public Optional<Task> get(TaskId id) {
        return byId(id);
    }


    @Override
    public List<Task> searchAll() {
        return all();
    }

    @Override
    public List<Task> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
