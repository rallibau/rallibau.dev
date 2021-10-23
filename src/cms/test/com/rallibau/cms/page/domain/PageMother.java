package com.rallibau.cms.page.domain;

import com.rallibau.cms.user.domain.User;
import com.rallibau.cms.user.domain.UserMother;

public final class PageMother {
    public static Page create(PageId id, User creator, PageTitle pageTitle, PageBody pageBody) {
        return Page.create(id, creator, pageTitle, pageBody);
    }

    public static Page random() {
        return Page.create(PageIdMother.random(),
                UserMother.random(),
                PageTitleMother.random(),
                PageBodyMother.random());
    }
}
