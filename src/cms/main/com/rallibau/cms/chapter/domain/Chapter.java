package com.rallibau.cms.chapter.domain;

import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.Identifier;

public class Chapter extends AggregateRoot {
    private final ChapterId id;
    private final ChapterTittle tittle;

    public Chapter(){
        this.id = null;
        this.tittle = null;
    }

    public Chapter(ChapterId id, ChapterTittle tittle) {
        this.id = id;
        this.tittle = tittle;
    }


    public static Chapter create(ChapterId id, ChapterTittle tittle) {
        return new Chapter(id, tittle);
    }


    public Identifier id() {
        return null;
    }

    public ChapterTittle tittle() {
        return tittle;
    }
}
