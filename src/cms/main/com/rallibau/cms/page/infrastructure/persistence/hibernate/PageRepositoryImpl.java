package com.rallibau.cms.page.infrastructure.persistence.hibernate;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageId;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("cms-transaction_manager")
public class PageRepositoryImpl extends HibernateRepository<Page,PageId> implements PageRepository {

    public PageRepositoryImpl(@Qualifier("cms-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Page.class);
    }

    @Override
    public void save(Page page) {
        persist(page);
    }

    @Override
    public Optional<Page> get(PageId id) {
        return byId(id);
    }


    @Override
    public List<Page> searchAll() {
        return all();
    }

    @Override
    public List<Page> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
