package com.rallibau.bpm.process.application.find;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.*;

public class ProceesFinderShould {

    private ProcessFinder processFinder;
    private ProcessRepository processRepository;
    List<Process> process = new ArrayList<>();

    @BeforeEach
    protected void setUp() {
        processRepository = mock(ProcessRepository.class);
        processFinder = new ProcessFinder(processRepository);

        process.add(ProcessMother.random());
        process.add(ProcessMother.random());
        process.add(ProcessMother.random());

        when(processRepository.searchAll()).thenReturn(process);
    }

    @Test
    public void find_curses_dont_failure() {
        assertThat(process, containsInAnyOrder(processFinder.find().toArray()));
        verify(processRepository, atLeastOnce()).searchAll();
    }
}
