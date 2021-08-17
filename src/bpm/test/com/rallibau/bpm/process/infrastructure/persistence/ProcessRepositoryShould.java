package com.rallibau.bpm.process.infrastructure.persistence;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessMother;
import com.rallibau.bpm.process.domain.ProcessRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class ProcessRepositoryShould {

    @Autowired
    protected ProcessRepository repository;

    @Test
    public void save_a_test_dont_failure(){
        Process process = ProcessMother.random();
        repository.save(process);
    }


}
