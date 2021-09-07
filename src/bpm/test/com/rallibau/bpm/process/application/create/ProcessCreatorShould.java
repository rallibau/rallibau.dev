package com.rallibau.bpm.process.application.create;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.bus.event.EventBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ProcessCreatorShould {
    private ProcessCreator processCreator;
    private ProcessRepository processRepository;
    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        processRepository = mock(ProcessRepository.class);
        eventBus = mock(EventBus.class);
        processCreator = new ProcessCreator(processRepository, eventBus);
    }

    @Test
    public void create_process_dont_failure() {
        Process process = ProcessMother.random();
        processCreator.create(process);
        verify(processRepository, atLeastOnce()).save(process);
    }


}
