package com.rallibau.apps.commons.config;


import com.rallibau.shared.domain.Service;
import com.rallibau.shared.domain.bus.command.CommandNotRegisteredError;
import com.rallibau.shared.domain.bus.query.QueryNotRegisteredError;
import com.rallibau.shared.infraestructure.bus.command.rabbitmq.command.ConsumeRabbitMqCommand;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.command.ConsumeRabbitMqDomainEvents;
import com.rallibau.shared.infraestructure.bus.query.rabbitmq.command.ConsumeRabbitMqQuery;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

@Service
public class RabbitMqConsumerAutoStarter implements ApplicationListener<ApplicationReadyEvent> {
    private static final String RABBIT = "rabbit";
    private final ConsumeRabbitMqDomainEvents consumeRabbitMqDomainEventsCommand;
    private final ConsumeRabbitMqCommand consumeRabbitMqCommand;
    private final ConsumeRabbitMqQuery consumeRabbitMqQuery;
    @Value("${mq.events.provider}")
    private String MQ_EVENTS_PROVIDER;
    @Value("${mq.commands.provider}")
    private String MQ_COMMANDS_PROVIDER;
    @Value("${mq.query.provider}")
    private String MQ_QUERY_PROVIDER;

    public RabbitMqConsumerAutoStarter(ConsumeRabbitMqDomainEvents consumeRabbitMqDomainEventsCommand,
                                       ConsumeRabbitMqCommand consumeRabbitMqCommand, ConsumeRabbitMqQuery consumeRabbitMqQuery) {
        this.consumeRabbitMqDomainEventsCommand = consumeRabbitMqDomainEventsCommand;
        this.consumeRabbitMqCommand = consumeRabbitMqCommand;
        this.consumeRabbitMqQuery = consumeRabbitMqQuery;
    }


    @Override
    public void onApplicationEvent(@NotNull ApplicationReadyEvent event) {
        if (RABBIT.equals(MQ_EVENTS_PROVIDER)) {
            consumeRabbitMqDomainEventsCommand.execute(new String[0]);
        }
        if (RABBIT.equals(MQ_COMMANDS_PROVIDER)) {
            try {
                consumeRabbitMqCommand.execute(new String[0]);
            } catch (CommandNotRegisteredError e) {
                e.printStackTrace();
            }
        }
        if (RABBIT.equals(MQ_QUERY_PROVIDER)) {
            try {
                consumeRabbitMqQuery.execute(new String[0]);
            } catch (QueryNotRegisteredError e) {
                e.printStackTrace();
            }
        }
    }
}