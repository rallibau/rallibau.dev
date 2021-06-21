package com.inetum.livetooling.lista.tag.infrastructure.persistence;

import com.inetum.livetooling.lista.tag.domain.Tag;
import com.inetum.livetooling.lista.tag.domain.TagId;
import com.inetum.livetooling.lista.tag.domain.TagRepository;
import com.inetum.livetooling.shared.domain.Service;
import com.inetum.livetooling.shared.domain.criteria.Criteria;
import com.inetum.livetooling.shared.infraestructure.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("lista-transaction_manager")
public class HibernateTagRepository extends HibernateRepository<Tag> implements TagRepository {
    public HibernateTagRepository(@Qualifier("lista-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Tag.class);
    }

    @Override
    public void save(Tag tag) {
        persist(tag);
    }

    @Override
    public Optional<Tag> get(TagId id) {
        return byId(id);
    }

    @Override
    public List<Tag> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}