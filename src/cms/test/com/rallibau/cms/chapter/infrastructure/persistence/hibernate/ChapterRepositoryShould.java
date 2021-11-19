package com.rallibau.cms.chapter.infrastructure.persistence.hibernate;

import com.rallibau.apps.cms.CmsApplication;
import com.rallibau.cms.chapter.domain.Chapter;
import com.rallibau.cms.chapter.domain.ChapterMother;
import com.rallibau.cms.chapter.domain.ChapterRepository;
import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageMother;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import javax.transaction.Transactional;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@ContextConfiguration(classes = CmsApplication.class)
@SpringBootTest
@Transactional
public class ChapterRepositoryShould {

    @Autowired
    private ChapterRepository repository;


    @Test
    protected void save_a_chapter() {
        Chapter chapter = ChapterMother.random();
        repository.save(chapter);
    }

    @Test
    public void search_by_criteria() {
        Chapter chapter = ChapterMother.random();
        repository.save(chapter);
        Criteria criteria = new Criteria(
                new Filters(
                        Collections.singletonList(
                                Filter.create("tittle",
                                        "contains",
                                        chapter.tittle().value()))),
                Order.asc("tittle"));
        assertThat(Collections.singletonList(chapter),
                containsInAnyOrder(repository.matching(criteria).toArray()));

    }
}
