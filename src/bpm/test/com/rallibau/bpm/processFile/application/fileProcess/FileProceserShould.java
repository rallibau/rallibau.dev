package com.rallibau.bpm.processFile.application.fileProcess;

import com.rallibau.bpm.processFile.domain.BpmModel;
import com.rallibau.bpm.processFile.domain.BpmModelExtractor;
import com.rallibau.bpm.processFile.domain.BpmModelExtractorException;
import com.rallibau.bpm.processFile.infrastructure.FileProcessorXmlImpl;
import com.rallibau.shared.domain.bus.command.CommandBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class FileProceserShould {
    private FileProcessor fileProcessor;
    private BpmModelExtractor bpmModelExtractor;

    @BeforeEach
    protected void setUp() {
        bpmModelExtractor = new FileProcessorXmlImpl();
        fileProcessor = new FileProcessor(mock(CommandBus.class), bpmModelExtractor);
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
