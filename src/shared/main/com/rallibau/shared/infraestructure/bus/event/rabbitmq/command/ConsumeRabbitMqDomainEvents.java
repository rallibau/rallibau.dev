package com.rallibau.shared.infraestructure.bus.event.rabbitmq.command;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import com.rallibau.shared.infraestructure.cli.ConsoleCommand;


@Service
public final class ConsumeRabbitMqDomainEvents extends ConsoleCommand {
    private final RabbitMqDomainEventsConsumer eventconsumer;


    public ConsumeRabbitMqDomainEvents(RabbitMqDomainEventsConsumer eventconsumer) {
        this.eventconsumer = eventconsumer;
    }

    @Override
    public void execute(String[] args) {
        eventconsumer.consume();
    }
}
