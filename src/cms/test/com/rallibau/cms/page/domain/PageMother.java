package com.rallibau.cms.page.domain;

public final class PageMother {
    public static Page create(PageId id, PageTitle pageTitle, PageBody pageBody){
        return Page.create(id,pageTitle,pageBody);
    }

    public static Page random(){
        return Page.create(PageIdMother.random(),PageTitleMother.random(),PageBodyMother.random());
    }
}
