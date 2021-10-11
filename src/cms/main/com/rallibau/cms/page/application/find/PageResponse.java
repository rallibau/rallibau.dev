package com.rallibau.cms.page.application.find;

import java.io.Serializable;

public class PageResponse implements Serializable {
    private String id;
    private String title;
    private String body;

    public PageResponse() {
        this.id = null;
        this.title = null;
        this.body = null;
    }

    public PageResponse(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
