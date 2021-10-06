package com.rallibau.apps.commons.config;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.command.ConsumeRabbitMqCommand;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.command.ConsumeRabbitMqDomainEvents;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@Service
public class RabbitMqConsumerAutoStarter implements ApplicationListener {
    private static final String RABBIT = "rabbit";
    private final ConsumeRabbitMqDomainEvents consumeRabbitMqDomainEventsCommand;
    private final ConsumeRabbitMqCommand consumeRabbitMqCommand;
    @Value("${mq.events.provider}")
    private String MQ_EVENTS_PROVIDER;
    @Value("${mq.commands.provider}")
    private String MQ_COMMANDS_PROVIDER;

    public RabbitMqConsumerAutoStarter(ConsumeRabbitMqDomainEvents consumeRabbitMqDomainEventsCommand, ConsumeRabbitMqCommand consumeRabbitMqCommand) {
        this.consumeRabbitMqDomainEventsCommand = consumeRabbitMqDomainEventsCommand;
        this.consumeRabbitMqCommand = consumeRabbitMqCommand;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent && RABBIT.equals(MQ_EVENTS_PROVIDER)) {
            consumeRabbitMqDomainEventsCommand.execute(new String[0]);
        }
        if (event instanceof ApplicationReadyEvent && RABBIT.equals(MQ_COMMANDS_PROVIDER)) {
            consumeRabbitMqCommand.execute(new String[0]);
        }
    }
}