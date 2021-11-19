package com.rallibau.cms.chapter.domain;

import com.rallibau.cms.page.domain.PageId;
import com.rallibau.cms.page.domain.PageIdMother;
import com.rallibau.shared.domain.UuidMother;

public class ChapterIdMother {
    public static ChapterId random() {
        return ChapterIdMother.create(UuidMother.random());
    }

    private static ChapterId create(String id) {
        return ChapterId.create(id);
    }
}
