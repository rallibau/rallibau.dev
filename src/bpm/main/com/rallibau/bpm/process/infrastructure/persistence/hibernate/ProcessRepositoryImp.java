package com.rallibau.bpm.process.infrastructure.persistence.hibernate;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("bpm-transaction_manager")
public  class ProcessRepositoryImp extends HibernateRepository<Process> implements ProcessRepository {


    public ProcessRepositoryImp(@Qualifier("bpm-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory,Process.class);
    }

    @Override
    public void save(Process process) {
        persist(process);
    }

    @Override
    public Optional<Process> get(ProcessId id) {
        return byId(id);
    }

    @Override
    public List<Process> searchAll() {
        return all();
    }

    @Override
    public List<Process> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
