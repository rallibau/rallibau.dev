package com.rallibau.cms.page.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.List;

public class PagesResponse implements Response {

    private final List<PageResponse> pages;

    public PagesResponse(List<PageResponse> pages) {
        this.pages = pages;
    }

    public List<PageResponse> getPages() {
        return pages;
    }
}
