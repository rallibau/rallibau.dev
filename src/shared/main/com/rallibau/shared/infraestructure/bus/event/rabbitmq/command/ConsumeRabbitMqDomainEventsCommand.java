package com.rallibau.shared.infraestructure.bus.event.rabbitmq.command;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import com.rallibau.shared.infraestructure.cli.ConsoleCommand;


@Service
public final class ConsumeRabbitMqDomainEventsCommand extends ConsoleCommand {
    private final RabbitMqDomainEventsConsumer consumer;

    public ConsumeRabbitMqDomainEventsCommand(RabbitMqDomainEventsConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void execute(String[] args) {
        consumer.consume();
    }
}
