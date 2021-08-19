package com.rallibau.bpm.process.domain;

import com.rallibau.shared.domain.StringValueObject;

public final class ProcessName extends StringValueObject {
    public ProcessName(String value) {
        super(value);
    }

    public ProcessName() {
        super("");
    }
}