package com.rallibau.apps.config;


import com.rallibau.shared.command.ConsumeRabbitMqDomainEventsCommand;
import com.rallibau.shared.domain.Service;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@Service
public class RabbitMqConsumerAutoStarter implements ApplicationListener {
    private final ConsumeRabbitMqDomainEventsCommand consumeRabbitMqDomainEventsCommand;

    public RabbitMqConsumerAutoStarter(ConsumeRabbitMqDomainEventsCommand consumeRabbitMqDomainEventsCommand) {
        this.consumeRabbitMqDomainEventsCommand = consumeRabbitMqDomainEventsCommand;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            consumeRabbitMqDomainEventsCommand.execute(new String[0]);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!RabbitMQ consumer has been started.");
        }
    }
}