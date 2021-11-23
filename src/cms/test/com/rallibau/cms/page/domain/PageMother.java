package com.rallibau.cms.page.domain;

public final class PageMother {
    public static Page create(PageId id, PageCreationDate pageCreationDate, PageIdUser pageIdUser, PageTitle pageTitle, PageBody pageBody) {
        return Page.create(id, pageCreationDate, pageIdUser, pageTitle, pageBody);
    }

    public static Page random() {
        return Page.create(
                PageIdMother.random(),
                PageCreationDateMother.random(),
                PageIdUserMother.random(),
                PageTitleMother.random(),
                PageBodyMother.random());
    }
}
