package com.rallibau.bpm.process.application.find;

import com.rallibau.shared.domain.bus.query.Response;

import java.util.List;

public class ProcessesResponse implements Response {
    private final List<ProcessResponse> processes;

    public ProcessesResponse(List<ProcessResponse> processes) {
        this.processes = processes;
    }

    public List<ProcessResponse> getProcesses() {
        return processes;
    }
}
