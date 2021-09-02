package com.rallibau.bpm.process.application.create;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import com.rallibau.shared.domain.bus.event.EventBus;
import com.rallibau.shared.domain.events.bpm.ProcessCreatedDomainEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class CreateProcessCommandHandlerShould {

    private CreateProcessCommandHandler createProcessCommandHandler;
    private ProcessRepository processRepository;
    protected EventBus eventBus;

    @BeforeEach
    protected void setUp() {
        processRepository = mock(ProcessRepository.class);
        eventBus      = mock(EventBus.class);
        createProcessCommandHandler = new CreateProcessCommandHandler(new ProcessCreator(processRepository,eventBus));
    }

    @Test
    public void create_valid_process() {
        Process process = ProcessMother.random();
        ProcessCreatedDomainEvent processCreatedDomainEvent = new ProcessCreatedDomainEvent(process.id().value(),process.name().value());
        CreateProcessCommand createProcessCommand = new CreateProcessCommand(process.id().value(),
                process.name().value(), new ArrayList<>());
        createProcessCommandHandler.handle(createProcessCommand);
        verify(processRepository, atLeastOnce()).save(process);
        verify(eventBus, atLeastOnce()).publish(Collections.singletonList(processCreatedDomainEvent));

    }
}
