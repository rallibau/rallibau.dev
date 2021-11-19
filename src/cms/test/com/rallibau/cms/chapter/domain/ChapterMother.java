package com.rallibau.cms.chapter.domain;

public class ChapterMother {
    public static Chapter create(ChapterId id , ChapterTittle tittle){
        return Chapter.create(id,tittle);
    }

    public static Chapter random(){
        return Chapter.create(ChapterIdMother.random(),ChapterTittleMother.random());
    }
}
