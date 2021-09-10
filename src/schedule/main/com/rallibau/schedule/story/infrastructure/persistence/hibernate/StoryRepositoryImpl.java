package com.rallibau.schedule.story.infrastructure.persistence.hibernate;

import com.rallibau.schedule.story.domain.Story;
import com.rallibau.schedule.story.domain.StoryId;
import com.rallibau.schedule.story.domain.StoryRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.hibernate.HibernateRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional("schedule-transaction_manager")
public class StoryRepositoryImpl extends HibernateRepository<Story> implements StoryRepository {

    public StoryRepositoryImpl(@Qualifier("schedule-session_factory") SessionFactory sessionFactory) {
        super(sessionFactory, Story.class);
    }

    @Override
    public void save(Story story) {
        persist(story);
    }

    @Override
    public Optional<Story> get(StoryId id) {
        return byId(id);
    }

    @Override
    public List<Story> searchAll() {
        return all();
    }

    @Override
    public List<Story> matching(Criteria criteria) {
        return byCriteria(criteria);
    }
}
