package com.rallibau.cms.page.domain;

import com.rallibau.shared.domain.DomainError;

public final class PageNotExist extends DomainError {
    public PageNotExist(PageId id) {
        super("node_not_exist", String.format("The node <%s> doesn't exist", id.value()));
    }
}
