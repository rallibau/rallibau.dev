package com.rallibau.bpm.node.application.addToProcess;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeMother;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class AddToProcessShould {

    private AddToProcess addToProcess;
    private ProcessRepository processRepository;
    private final Process process = ProcessMother.random();

    @BeforeEach
    protected void setUp() {
        processRepository = mock(ProcessRepository.class);

        when(processRepository.get(process.id())).thenReturn(Optional.of(process));

        addToProcess = new AddToProcess(processRepository);
    }

    @Test
    public void we_can_add_node_to_process() {
        Optional<Process> process = processRepository.get(this.process.id());
        if(process.isPresent()){
            Node node = NodeMother.random();
            addToProcess.addNode(process.get(),node);
            verify(processRepository, atLeastOnce()).save(process.get());
        }
    }
}
