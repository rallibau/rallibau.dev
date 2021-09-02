package com.rallibau.bpm.process.application.create;

import com.rallibau.bpm.node.domain.Node;
import com.rallibau.bpm.node.domain.NodeId;
import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessName;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandHandler;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class CreateProcessCommandHandler implements CommandHandler<CreateProcessCommand> {

    private final ProcessCreator processCreator;

    public CreateProcessCommandHandler(ProcessCreator processCreator) {
        this.processCreator = processCreator;
    }


    @Override
    public void handle(CreateProcessCommand command) {

        processCreator.create(Process.create(new ProcessId(command.id()),
                new ProcessName(command.name()), command.nodesId().stream().map(value -> new NodeId(value)).collect(Collectors.toList())));
    }
}
