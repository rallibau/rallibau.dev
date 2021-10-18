package com.rallibau.cms.page.domain;

public final class PageMother {
    public static Page create(PageId id, PageIdUser pageIdUser, PageTitle pageTitle, PageBody pageBody) {
        return Page.create(id, pageIdUser, pageTitle, pageBody);
    }

    public static Page random() {
        return Page.create(PageIdMother.random(),
                PageIdUserMother.random(),
                PageTitleMother.random(),
                PageBodyMother.random());
    }
}
