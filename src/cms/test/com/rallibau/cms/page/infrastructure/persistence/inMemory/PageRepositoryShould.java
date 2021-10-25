package com.rallibau.cms.page.infrastructure.persistence.inMemory;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageMother;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.domain.criteria.Filter;
import com.rallibau.shared.domain.criteria.Filters;
import com.rallibau.shared.domain.criteria.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;


public class PageRepositoryShould {

    private PageRepositoryInMemoryImpl repository;

    @BeforeEach
    public void prepare() {
        repository = new PageRepositoryInMemoryImpl();
    }


    @Test
    protected void save_a_page() {
        repository.save(PageMother.random());

    }


    @Test
    public void search_all_existing_page() {
        Page page = PageMother.random();
        repository.save(page);
        Page page2 = PageMother.random();
        repository.save(page2);
        assertThat(Arrays.asList(page, page2), containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    public void search_by_criteria() {
        Page page = PageMother.random();
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
