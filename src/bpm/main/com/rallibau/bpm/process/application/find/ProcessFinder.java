package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessNotExist;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Monitor;
import com.rallibau.shared.domain.Service;

import java.util.List;

@Service
public class ProcessFinder {
    private final ProcessRepository processRepository;

    public ProcessFinder(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Monitor
    public Process find(String id) throws ProcessNotExist {
        return processRepository.get(new ProcessId(id)).orElseThrow(() -> new ProcessNotExist(new ProcessId(id)));
    }

    @Monitor
    public List<Process> find() {
        return processRepository.searchAll();
    }


}
