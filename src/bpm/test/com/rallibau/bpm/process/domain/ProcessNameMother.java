package com.rallibau.bpm.process.domain;

import com.rallibau.shared.domain.WordMother;

public class ProcessNameMother {

    public static ProcessName create(String value){
        return new ProcessName(value);
    }

    public static ProcessName random() {
        return new ProcessName(WordMother.random());
    }
}
