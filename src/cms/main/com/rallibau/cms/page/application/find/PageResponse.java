package com.rallibau.cms.page.application.find;

import com.rallibau.shared.domain.bus.query.Response;

public class PageResponse implements Response {
    private final String id;
    private final String title;
    private final String body;
    private final String author;

    public PageResponse() {
        this.author = null;
        this.id = null;
        this.title = null;
        this.body = null;
    }

    public PageResponse(String id, String title, String body, String author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public String getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getBody() {
        return body;
    }


    public String getAuthor() {
        return author;
    }
}
