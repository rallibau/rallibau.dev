package com.inetum.livetooling.viaje.aclaracion.infrastructure;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.criteria.Criteria;
import com.inetum.livetooling.shared.infraestructure.hibernate.HibernateRepository;
import com.inetum.livetooling.viaje.aclaracion.domain.Aclaracion;
import com.inetum.livetooling.viaje.aclaracion.domain.AclaracionId;
import com.inetum.livetooling.viaje.aclaracion.domain.AclaracionRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("viaje-transaction_manager")
public class HiberneteAclaracionRepository extends HibernateRepository<Aclaracion> implements AclaracionRepository {
    public HiberneteAclaracionRepository(@Qualifier("viaje-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory, Aclaracion.class);
    }

    @Override
    public void save(Aclaracion aclaracion) {
        persist(aclaracion);
    }

    @Override
    public Optional<Aclaracion> get(AclaracionId id) {
        return byId(id);
    }

    @Override
    public List<Aclaracion> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
