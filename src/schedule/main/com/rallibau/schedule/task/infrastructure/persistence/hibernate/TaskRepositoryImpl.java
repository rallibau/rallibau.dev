package com.rallibau.schedule.task.infrastructure.persistence.hibernate;

import com.rallibau.schedule.task.domain.Task;
import com.rallibau.schedule.task.domain.TaskId;
import com.rallibau.schedule.task.domain.TaskRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("schedule-transaction_manager")
public class TaskRepositoryImpl extends HibernateRepository<Task, TaskId> implements TaskRepository {

    public TaskRepositoryImpl(@Qualifier("schedule-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Task.class);
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
