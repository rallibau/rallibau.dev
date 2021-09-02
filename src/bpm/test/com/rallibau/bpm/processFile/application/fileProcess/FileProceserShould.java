package com.rallibau.bpm.processFile.application.fileProcess;

import com.rallibau.apps.bpm.BpmApplication;
import com.rallibau.bpm.processFile.domain.BpmModel;
import com.rallibau.shared.domain.bus.command.CommandBus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@ContextConfiguration(classes = BpmApplication.class)
@SpringBootTest
public class FileProceserShould {
    @Autowired
    private CommandBus commandBus;
    private FileProcesor fileProcesor;

    @BeforeEach
    protected void setUp() {
        //commandBus = mock(CommandBus.class);
        fileProcesor = new FileProcesor(commandBus);
    }

    @Test
    public void create_process_from_file() throws ParserConfigurationException, IOException, SAXException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("diagram _pizzas.bpmn");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        File file = new File(resource.toURI());


        List<BpmModel> bpmModels = fileProcesor.process(file);
        assertThat("Obtenemos "+bpmModels.size()+"en lugar de 2 procesos", bpmModels.size() == 2);
        fileProcesor.persist(bpmModels);

    }

}
