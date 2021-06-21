package com.inetum.livetooling.apps.gestionViaje.command;

import com.inetum.livetooling.shared.infraestructure.bus.event.rabbitmq.RabbitMqDomainEventsConsumer;
import com.inetum.livetooling.shared.infraestructure.cli.ConsoleCommand;

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
