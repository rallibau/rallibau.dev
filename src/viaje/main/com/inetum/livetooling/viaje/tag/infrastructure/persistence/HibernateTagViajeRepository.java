package com.inetum.livetooling.viaje.tag.infrastructure.persistence;

import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.criteria.Criteria;
import com.inetum.livetooling.shared.infraestructure.hibernate.HibernateRepository;
import com.inetum.livetooling.viaje.tag.domain.TagViajes;
import com.inetum.livetooling.viaje.tag.domain.TagViajesId;
import com.inetum.livetooling.viaje.tag.domain.TagViajesRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("viaje-transaction_manager")
public class HibernateTagViajeRepository extends HibernateRepository<TagViajes>  implements TagViajesRepository {

    public HibernateTagViajeRepository(@Qualifier("viaje-session_factory")SessionFactory sessionFactory) {
        super(sessionFactory, TagViajes.class);
    }

    @Override
    public void save(TagViajes tag) {
        persist(tag);
    }


    @Override
    public List<TagViajes> matching(Criteria criteria) {
        return byCriteria(criteria);
    }

    @Override
    public Optional<TagViajes> get(TagViajesId id) {
        return byId(id);
    }
}
