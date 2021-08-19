package com.rallibau.bpm.process.application;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class CreateProcessCommandHandlerShould {

    private CreateProcessCommandHandler createProcessCommandHandler;
    private ProcessCreator processCreator;

    @BeforeEach
    protected void setUp() {
        processCreator = mock(ProcessCreator.class);
        createProcessCommandHandler = new CreateProcessCommandHandler(processCreator);
    }

    @Test
    public void create_valid_process() {
        Process process = ProcessMother.random();
        CreateProcessCommand createProcessCommand = new CreateProcessCommand(process.id().value(),
                process.name().value());
        createProcessCommandHandler.handle(createProcessCommand);
        verify(processCreator, atLeastOnce()).create(process);

    }
}
