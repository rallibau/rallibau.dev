package com.rallibau.bpm.process.application.find;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class ProceesFinderShould {

    private ProcessFinder processFinder;
    private ProcessRepository processRepository;
    List<Process> procesos = new ArrayList<>();

    @BeforeEach
    protected void setUp() {
        processRepository = mock(ProcessRepository.class);
        processFinder = new ProcessFinder(processRepository);

        procesos.add(ProcessMother.random());
        procesos.add(ProcessMother.random());
        procesos.add(ProcessMother.random());

        when(processRepository.searchAll()).thenReturn(procesos);
    }

    @Test
    public void find_curses_dont_failure(){
        assertThat(procesos,containsInAnyOrder(processFinder.findAll().processList().toArray()));
        verify(processRepository, atLeastOnce()).searchAll();
    }
}
