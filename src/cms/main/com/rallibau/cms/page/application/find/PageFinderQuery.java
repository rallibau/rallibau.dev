package com.rallibau.cms.page.application.find;

import com.rallibau.shared.domain.bus.query.Query;
import com.rallibau.shared.domain.bus.query.Response;

import java.io.Serializable;
import java.util.HashMap;

public final class PageFinderQuery extends Query {
    private final String pageId;

    public PageFinderQuery() {
        this.pageId = null;
    }

    public PageFinderQuery(String pageId) {
        this.pageId = pageId;
    }

    public String pageId() {
        return pageId;
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("pageId", pageId);
        }};
    }

    @Override
    public Query fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new PageFinderQuery((String) body.get("pageId"));
    }

    @Override
    public Response parseResponse(String message) {
        return null;
    }
}
