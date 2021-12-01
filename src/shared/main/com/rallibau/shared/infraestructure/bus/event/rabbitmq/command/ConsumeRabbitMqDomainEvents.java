package com.rallibau.shared.infraestructure.bus.event.rabbitmq.command;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import com.rallibau.shared.infraestructure.cli.ConsoleCommand;


@Service
public final class ConsumeRabbitMqDomainEvents extends ConsoleCommand {
    private final RabbitMqDomainEventsConsumer eventConsumer;


    public ConsumeRabbitMqDomainEvents(RabbitMqDomainEventsConsumer eventConsumer) {
        this.eventConsumer = eventConsumer;
    }

    @Override
    public void execute(String[] args) {
        eventConsumer.consume();
    }
}
