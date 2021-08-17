package com.rallibau.bpm.process.domain;

import com.rallibau.shared.domain.UuidMother;

public class ProcessIdMohter {

    public static ProcessId create(String value){
        return new ProcessId(value);
    }

    public static ProcessId random() {
        return new ProcessId(UuidMother.random());
    }
}
