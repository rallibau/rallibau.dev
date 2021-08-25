package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Service;

import java.util.List;

@Service
public class ProcessFinder {
    private final ProcessRepository processRepository;

    public ProcessFinder(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public ProcessResponse findAll() {
        return new ProcessResponse(processRepository.searchAll());
    }
}