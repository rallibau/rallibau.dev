package com.rallibau.cms.page.infrastructure.persistence.inMemory;

import com.rallibau.cms.page.domain.Page;
import com.rallibau.cms.page.domain.PageId;
import com.rallibau.cms.page.domain.PageRepository;
import com.rallibau.shared.domain.criteria.Criteria;
import com.rallibau.shared.infraestructure.persistence.memory.MemoryRepository;

import java.util.List;
import java.util.Optional;

public class PageRepositoryInMemoryImpl extends MemoryRepository<Page, PageId> implements PageRepository {

    public PageRepositoryInMemoryImpl() {
        super(Page.class);
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
