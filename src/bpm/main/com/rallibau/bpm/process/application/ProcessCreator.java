package com.rallibau.bpm.process.application;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Service;

@Service
public class ProcessCreator {

    private final ProcessRepository processRepository;

    public ProcessCreator(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public void create(Process process) {
        processRepository.save(process);
    }
}
