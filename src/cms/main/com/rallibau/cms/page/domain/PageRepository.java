package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface PageRepository {
    void save(Page user);

    Optional<Page> get(PageId id);

    List<Page> searchAll();

    List<Page> matching(Criteria criteria);

}
