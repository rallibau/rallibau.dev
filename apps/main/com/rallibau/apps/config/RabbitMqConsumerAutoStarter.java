package com.rallibau.apps.config;


import com.rallibau.shared.command.ConsumeRabbitMqDomainEventsCommand;
import com.rallibau.shared.domain.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@Service
public class RabbitMqConsumerAutoStarter implements ApplicationListener {
    private static final String RABBIT = "rabbit";
    private final ConsumeRabbitMqDomainEventsCommand consumeRabbitMqDomainEventsCommand;
    @Value("${mq.provider}")
    private String MQ_PROVIDER;

    public RabbitMqConsumerAutoStarter(ConsumeRabbitMqDomainEventsCommand consumeRabbitMqDomainEventsCommand) {
        this.consumeRabbitMqDomainEventsCommand = consumeRabbitMqDomainEventsCommand;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent && RABBIT.equals(MQ_PROVIDER)) {
            consumeRabbitMqDomainEventsCommand.execute(new String[0]);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!RabbitMQ consumer has been started.");
        }
    }
}