package com.rallibau.cms.chapter.domain;

import com.rallibau.shared.domain.WordMother;

public class ChapterTittleMother {
    public static ChapterTittle random() {
        return ChapterTittle.create(WordMother.random());
    }
}
