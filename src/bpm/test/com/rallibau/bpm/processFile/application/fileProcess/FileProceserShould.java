package com.rallibau.bpm.processFile.application.fileProcess;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.processFile.domain.BpmModel;
import com.rallibau.bpm.processFile.domain.BpmModelExtractor;
import com.rallibau.bpm.processFile.domain.BpmModelExtractorException;
import com.rallibau.shared.domain.bus.command.CommandBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class FileProceserShould {
    @Autowired
    private CommandBus commandBus;
    private FileProcessor fileProcessor;
    @Autowired
    private BpmModelExtractor bpmModelExtractor;

    @BeforeEach
    protected void setUp() {
        //commandBus = mock(CommandBus.class);
        fileProcessor = new FileProcessor(commandBus, bpmModelExtractor);
    }

    @Test
    public void create_process_from_file() throws URISyntaxException, BpmModelExtractorException {
        URL resource = getClass().getClassLoader().getResource("diagram _pizzas.bpmn");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        List<BpmModel> bpmModels = fileProcessor.process(resource.toURI().getPath());
        assertThat("Obtenemos " + bpmModels.size() + "en lugar de 2 procesos", bpmModels.size() == 2);
        fileProcessor.persist(bpmModels);

    }

}
