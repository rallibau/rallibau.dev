package com.rallibau.cms.chapter.domain;

import com.rallibau.shared.domain.StringValueObject;

public class ChapterTittle extends StringValueObject {
    public ChapterTittle(){
        super("");

    }
    public ChapterTittle(String value) {
        super(value);
    }

    public static ChapterTittle create(String value) {
        return new ChapterTittle(value);
    }
}
