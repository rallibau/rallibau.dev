package com.rallibau.cms.chapter.infrastructure.persistence.hibernate;

import com.rallibau.cms.chapter.domain.Chapter;
import com.rallibau.cms.chapter.domain.ChapterId;
import com.rallibau.cms.chapter.domain.ChapterRepository;
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
public class ChapterRepositoryImpl extends HibernateRepository<Chapter, ChapterId> implements ChapterRepository {

    public ChapterRepositoryImpl(@Qualifier("cms-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Chapter.class);
    }

    @Override
    public void save(Chapter chapter) {
        persist(chapter);
    }

    @Override
    public Optional<Chapter> get(ChapterId id) {
        return byId(id);
    }


    @Override
    public List<Chapter> searchAll() {
        return all();
    }

    @Override
    public List<Chapter> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
