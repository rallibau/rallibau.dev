package com.rallibau.cms.page.domain;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserIdMother;
import com.rallibau.cms.user.domain.UserMother;

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
