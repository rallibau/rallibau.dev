package com.inetum.livetooling.apps.gestionViaje.config;

import com.inetum.livetooling.apps.gestionViaje.command.ConsumeRabbitMqDomainEventsCommand;
import com.inetum.livetooling.shared.domain.Service;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

@Service
public class RabbitMqConsumerAutoStarter implements ApplicationListener {
    private final ConsumeRabbitMqDomainEventsCommand consumeRabbitMqDomainEventsCommand;
    private final Logger logger;

    public RabbitMqConsumerAutoStarter(ConsumeRabbitMqDomainEventsCommand consumeRabbitMqDomainEventsCommand) {
        logger = LogManager.getLogger(RabbitMqConsumerAutoStarter.class);
        this.consumeRabbitMqDomainEventsCommand = consumeRabbitMqDomainEventsCommand;
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent) {
            consumeRabbitMqDomainEventsCommand.execute(new String[0]);
            logger.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!RabbitMQ consumer has been started.");
        }
    }
}

