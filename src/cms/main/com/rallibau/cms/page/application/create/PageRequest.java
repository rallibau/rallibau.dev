package com.rallibau.cms.page.application.create;

import java.io.Serializable;

public class PageRequest implements Serializable {
    private  String title;
    private  String body;

    public PageRequest() {
        this.title = null;
        this.body = null;
    }

    public PageRequest(String title, String body) {
        this.title = title;
        this.body = body;
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
