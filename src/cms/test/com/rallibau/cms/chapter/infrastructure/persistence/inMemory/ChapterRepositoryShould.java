package com.rallibau.cms.chapter.infrastructure.persistence.inMemory;

import com.rallibau.cms.chapter.domain.Chapter;
import com.rallibau.cms.chapter.domain.ChapterMother;
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


public class ChapterRepositoryShould {

    private ChapterRepositoryInMemoryImpl repository;

    @BeforeEach
    public void prepare() {
        repository = new ChapterRepositoryInMemoryImpl();
    }


    @Test
    protected void save_a_page() {
        repository.save(ChapterMother.random());

    }


    @Test
    public void search_all_existing_page() {
        Chapter chapter = ChapterMother.random();
        repository.save(chapter);
        Chapter chapter2 = ChapterMother.random();
        repository.save(chapter2);
        assertThat(Arrays.asList(chapter, chapter2), containsInAnyOrder(repository.searchAll().toArray()));
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
                Order.asc("pageTitle"));
        assertThat(Collections.singletonList(chapter),
                containsInAnyOrder(repository.matching(criteria).toArray()));

    }
}
