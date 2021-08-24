package com.rallibau.bpm.process.application.create;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.event.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class ProcessCreator {

    private final ProcessRepository processRepository;
    private final EventBus eventBus;
    private static final Logger logger = LogManager.getLogger(ProcessCreator.class);

    public ProcessCreator(ProcessRepository processRepository, EventBus eventBus) {
        this.processRepository = processRepository;
        this.eventBus = eventBus;
    }

    public void create(Process process) {
        processRepository.save(process);
        logger.info("Process "+process.id().value()+" crated");
        eventBus.publish(process.pullDomainEvents());
    }
}
