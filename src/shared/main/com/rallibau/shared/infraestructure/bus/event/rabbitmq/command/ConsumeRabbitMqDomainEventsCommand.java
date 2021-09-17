package com.rallibau.shared.infraestructure.bus.event.rabbitmq.command;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.infraestructure.bus.command.rabbitmq.RabbitMqCommandsConsumer;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import com.rallibau.shared.infraestructure.cli.ConsoleCommand;


@Service
public final class ConsumeRabbitMqDomainEventsCommand extends ConsoleCommand {
    private final RabbitMqDomainEventsConsumer eventconsumer;
    private final RabbitMqCommandsConsumer commandsConsumer;

    public ConsumeRabbitMqDomainEventsCommand(RabbitMqDomainEventsConsumer eventconsumer, RabbitMqCommandsConsumer commandsConsumer) {
        this.eventconsumer = eventconsumer;
        this.commandsConsumer = commandsConsumer;
    }

    @Override
    public void execute(String[] args) {
        eventconsumer.consume();
        commandsConsumer.consume();
    }
}
