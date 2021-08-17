package com.rallibau.bpm.process.domain;

public final class ProcessMother {
    public static Process create(ProcessId id, ProcessName name) {
        return Process.create(id,name);
    }

    public static Process random() {
        return Process.create(ProcessIdMohter.random(),ProcessNameMother.random());
    }
}
