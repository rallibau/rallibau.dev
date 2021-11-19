package com.rallibau.cms.chapter.domain;

import com.rallibau.shared.domain.Identifier;

public class ChapterId extends Identifier {
    public ChapterId(String value) {
        super(value);
    }

    public ChapterId() {
    }

    public static ChapterId create(String value) {
        return new ChapterId(value);
    }
}
