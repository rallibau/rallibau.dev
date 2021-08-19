package com.rallibau.bpm.process.application;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class ProcessCreatorShould {
    private ProcessCreator processCreator;
    @Autowired
    private ProcessRepository processRepository;

    @BeforeEach
    protected void setUp() {
        processRepository = mock(ProcessRepository.class);
        processCreator = new ProcessCreator(processRepository);
    }

    @Test
    public void create_process_dont_failure(){
        Process process = ProcessMother.random();
        processCreator.create(process);
        verify(processRepository, atLeastOnce()).save(process);
    }


}
