package com.rallibau.cms.chapter.domain;

import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.Identifier;

import java.util.Objects;

public class Chapter extends AggregateRoot {
    private final ChapterId id;
    private final ChapterTittle tittle;

    public Chapter() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chapter chapter = (Chapter) o;
        return id.equals(chapter.id) &&
                tittle().value().equals(chapter.tittle().value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tittle);
    }


    public Identifier id() {
        return null;
    }

    public ChapterTittle tittle() {
        return tittle;
    }
}
