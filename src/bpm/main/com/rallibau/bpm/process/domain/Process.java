package com.rallibau.bpm.process.domain;

import com.rallibau.shared.domain.AggregateRoot;
import com.rallibau.shared.domain.events.bpm.CreateProcessDomainEvent;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Process cliente = (Process) o;
        return id.equals(cliente.id) &&
                name.equals(cliente.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public static Process create(ProcessId id, ProcessName name) {
        Process process = new Process(id, name);
        process.record(new CreateProcessDomainEvent(id.value(),name.value()));
        return process;
    }

    public ProcessId id(){
        return id;
    }

    public ProcessName name(){
        return name;
    }

}
