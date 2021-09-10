package com.rallibau.apps.bpm.controller.processFile;

import com.rallibau.bpm.processFile.application.fileProcess.FileProcessor;
import com.rallibau.bpm.processFile.domain.BpmModel;
import com.rallibau.bpm.processFile.domain.BpmModelExtractorException;
import com.rallibau.shared.domain.DomainError;
import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.command.CommandHandlerExecutionError;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.spring.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProcessFilePutController extends ApiController {

    @Autowired
    private FileProcessor fileProcessor;

    public ProcessFilePutController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @PutMapping(value = "/processFile")
    public ResponseEntity<String> index() throws CommandHandlerExecutionError, BpmModelExtractorException, URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("diagram_guardia_civil.bpmn");
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }
        List<BpmModel> bpmModels = fileProcessor.process(resource.toURI().getPath());
        fileProcessor.persist(bpmModels);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public HashMap<Class<? extends DomainError>, HttpStatus> errorMapping() {
        return null;
    }
}
