package com.inetum.livetooling.saldo.clientes.infrastructure.persistence;

import com.inetum.livetooling.saldo.clientes.domain.Cliente;
import com.inetum.livetooling.saldo.clientes.domain.ClienteId;
import com.inetum.livetooling.saldo.clientes.domain.ClienteRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("facturacion-transaction_manager")
public class HibernateClienteRepository extends HibernateRepository<Cliente> implements ClienteRepository {
    public HibernateClienteRepository(@Qualifier("facturacion-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory, Cliente.class);
    }

    @Override
    public void save(Cliente cliente) {
        persist(cliente);
    }

    @Override
    public Optional<Cliente> get(ClienteId id) {
        return byId(id);
    }

    @Override
    public List<Cliente> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
