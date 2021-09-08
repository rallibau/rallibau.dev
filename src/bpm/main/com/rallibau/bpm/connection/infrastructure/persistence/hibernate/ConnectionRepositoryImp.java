package com.rallibau.bpm.connection.infrastructure.persistence.hibernate;

import com.rallibau.bpm.connection.domain.Connection;
import com.rallibau.bpm.connection.domain.ConnectionId;
import com.rallibau.bpm.connection.domain.ConnectionRepository;
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
public class ConnectionRepositoryImp extends HibernateRepository<Connection> implements ConnectionRepository {

    public ConnectionRepositoryImp(@Qualifier("bpm-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Connection.class);
    }

    @Override
    public void save(Connection connection) {
        persist(connection);
    }

    @Override
    public Optional<Connection> get(ConnectionId id) {
        return byId(id);
    }

    @Override
    public List<Connection> searchAll() {
        return all();
    }

    @Override
    public List<Connection> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
