package com.rallibau.shared.infraestructure.bus.event.rabbitmq.command;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.infraestructure.bus.command.rabbitmq.RabbitMqCommandsConsumer;
import com.rallibau.shared.infraestructure.cli.ConsoleCommand;


@Service
public final class ConsumeRabbitMqCommand extends ConsoleCommand {
    private final RabbitMqCommandsConsumer commandsConsumer;

    public ConsumeRabbitMqCommand(RabbitMqCommandsConsumer commandsConsumer) {
        this.commandsConsumer = commandsConsumer;
    }

    @Override
    public void execute(String[] args) {
        commandsConsumer.consume();
    }
}
