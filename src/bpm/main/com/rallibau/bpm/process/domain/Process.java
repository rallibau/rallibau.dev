package com.rallibau.bpm.process.domain;

import com.rallibau.shared.domain.AggregateRoot;

public class Process extends AggregateRoot {
    private final ProcessId id;
    private final ProcessName name;

    public Process(ProcessId id, ProcessName name) {
        this.id = id;
        this.name = name;
    }

    public Process() {
        this.id = null;
        this.name = null;
    }

    public static Process create(ProcessId id, ProcessName name) {
        return new Process(id, name);
    }
}
