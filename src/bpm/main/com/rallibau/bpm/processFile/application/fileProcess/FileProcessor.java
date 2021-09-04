package com.rallibau.bpm.processFile.application.fileProcess;

import com.rallibau.bpm.node.application.create.CreateNodeCommand;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.process.application.create.CreateProcessCommand;
import com.rallibau.bpm.processFile.domain.BpmModel;
import com.rallibau.bpm.processFile.domain.BpmModelExtractor;
import com.rallibau.bpm.processFile.domain.BpmModelExtractorException;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandBus;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileProcessor {

    private final CommandBus commandBus;
    private final BpmModelExtractor bpmModelExtractor;

    public FileProcessor(CommandBus commandBus, BpmModelExtractor bpmModelExtractor) {
        this.commandBus = commandBus;
        this.bpmModelExtractor = bpmModelExtractor;
    }

    public List<BpmModel> process(String filePath) throws BpmModelExtractorException {
        return bpmModelExtractor.extractProcess(filePath);
    }

    public void persist(List<BpmModel> bpmModels) {
        bpmModels.forEach(bpmModel -> commandBus.dispatch(new CreateProcessCommand(bpmModel.process().id().value(),
                bpmModel.process().name().value(), bpmModel.process().nodes().stream().map(NodeId::value).collect(Collectors.toList()))));

        bpmModels.forEach(bpmModel -> bpmModel.nodes().forEach(node -> commandBus.dispatch(new CreateNodeCommand(node.id().value(),
                node.name().value(),
                node.nodeType().value()))));
    }

}
