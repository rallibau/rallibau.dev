package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Monitor;
import com.rallibau.shared.domain.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProcessFinder {
    private final ProcessRepository processRepository;

    public ProcessFinder(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Monitor
    public Optional<Process> find(String id) {
        return processRepository.get(new ProcessId(id));
    }

    @Monitor
    public List<Process> find() {
        return processRepository.searchAll();
    }


}
