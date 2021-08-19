package com.rallibau.bpm.process.application.create;

import com.rallibau.bpm.process.domain.Process;
import com.rallibau.bpm.process.domain.ProcessId;
import com.rallibau.bpm.process.domain.ProcessName;
import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandHandler;

@Service
public class CreateProcessCommandHandler implements CommandHandler<CreateProcessCommand> {

    private final ProcessCreator processCreator;

    public CreateProcessCommandHandler(ProcessCreator processCreator) {
        this.processCreator = processCreator;
    }


    @Override
    public void handle(CreateProcessCommand command) {

        processCreator.create(Process.create(new ProcessId(command.id()),
                new ProcessName(command.name())));
    }
}
