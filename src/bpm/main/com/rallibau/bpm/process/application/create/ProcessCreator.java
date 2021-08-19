package com.rallibau.bpm.process.application.create;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.EventBus;

@Service
public class ProcessCreator {

    private final ProcessRepository processRepository;
    private final EventBus eventBus;

    public ProcessCreator(ProcessRepository processRepository, EventBus eventBus) {
        this.processRepository = processRepository;
        this.eventBus = eventBus;
    }

    public void create(Process process) {
        processRepository.save(process);
        eventBus.publish(process.pullDomainEvents());
    }
}
