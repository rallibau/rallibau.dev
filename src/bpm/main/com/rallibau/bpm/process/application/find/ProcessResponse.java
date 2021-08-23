package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.shared.domain.bus.query.Response;

import java.util.List;

public class ProcessResponse implements Response {
    private final  List<Process> processList;

    public ProcessResponse(List<Process> processList) {
        this.processList = processList;
    }

    public List<Process> processList() {
        return processList;
    }
}
