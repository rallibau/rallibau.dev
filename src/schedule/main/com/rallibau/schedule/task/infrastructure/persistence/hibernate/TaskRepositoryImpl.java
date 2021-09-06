package com.rallibau.schedule.task.infrastructure.persistence.hibernate;

import com.rallibau.schedule.task.domain.Task;
import com.rallibau.schedule.task.domain.TaskId;
import com.rallibau.schedule.task.domain.TaskRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("schedule-transaction_manager")
public  class TaskRepositoryImpl extends HibernateRepository<Task> implements TaskRepository {

    public TaskRepositoryImpl(@Qualifier("schedule-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory,Task.class);
    }

    @Override
    public void save(Task random) {

    }

    @Override
    public Optional<Task> get(TaskId id) {
        return Optional.empty();
    }

    @Override
    public List<Task> searchAll() {
        return null;
    }

    @Override
    public List<Task> matching(Criteria criteria) {
        return null;
    }
}
