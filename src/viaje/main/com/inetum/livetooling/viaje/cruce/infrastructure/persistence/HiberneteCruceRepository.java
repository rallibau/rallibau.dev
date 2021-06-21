package com.inetum.livetooling.viaje.cruce.infrastructure.persistence;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.criteria.Criteria;
import com.inetum.livetooling.shared.infraestructure.hibernate.HibernateRepository;
import com.inetum.livetooling.viaje.cruce.domain.Cruce;
import com.inetum.livetooling.viaje.cruce.domain.CruceId;
import com.inetum.livetooling.viaje.cruce.domain.CruceRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("viaje-transaction_manager")
public class HiberneteCruceRepository extends HibernateRepository<Cruce> implements CruceRepository {
    public HiberneteCruceRepository(@Qualifier("viaje-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory, Cruce.class);
    }

    @Override
    public void save(Cruce cruce) {
        persist(cruce);
    }

    @Override
    public Optional<Cruce> get(CruceId id) {
        return byId(id);
    }

    @Override
    public List<Cruce> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
