package com.rallibau.bpm.node.application.addToProcess;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Service;

@Service
public class AddToProcess {

    private final ProcessRepository processRepository;

    public AddToProcess(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    public void addNode(Process process, Node node) {
        process.addNode(node.id());
        processRepository.save(process);
    }
}
