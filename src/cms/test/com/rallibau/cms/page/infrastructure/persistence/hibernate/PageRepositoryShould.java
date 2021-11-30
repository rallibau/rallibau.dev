package com.rallibau.cms.page.infrastructure.persistence.hibernate;

import com.rallibau.apps.cms.CmsApplication;
import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageMother;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserCmsRepository;
import com.rallibau.cms.user.domain.UserMother;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
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
public class PageRepositoryShould {

    @Autowired
    private PageRepository repository;

    @Autowired
    private UserCmsRepository userCmsRepository;

    User user = null;

    @BeforeEach
    public void setUp() {
        user = UserMother.random();
        userCmsRepository.save(user);
    }


    @Test
    protected void save_a_page() {
        Page page = PageMother.random();
        page.setUser(user);
        repository.save(page);
    }

    @Test
    public void search_by_criteria() {
        Page page = PageMother.random();
        page.setUser(user);
        repository.save(page);
        Criteria criteria = new Criteria(
                new Filters(
                        Collections.singletonList(
                                Filter.create("pageTitle",
                                        "contains",
                                        page.pageTitle().value()))),
                Order.asc("pageTitle"));
        assertThat(Collections.singletonList(page),
                containsInAnyOrder(repository.matching(criteria).toArray()));

    }
}
