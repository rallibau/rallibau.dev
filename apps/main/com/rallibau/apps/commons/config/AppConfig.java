package com.rallibau.apps.commons.config;

import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.event.EventBus;
import com.rallibau.shared.domain.bus.query.QueryBus;
import com.rallibau.shared.infraestructure.bus.command.CommandHandlersInformation;
import com.rallibau.shared.infraestructure.bus.command.inMemory.InMemoryCommandBus;
import com.rallibau.shared.infraestructure.bus.command.rabbitmq.RabbitCommandBus;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqEventBus;
import com.rallibau.shared.infraestructure.bus.event.spring.SpringApplicationEventBus;
import com.rallibau.shared.infraestructure.bus.query.QueryHandlersInformation;
import com.rallibau.shared.infraestructure.bus.query.inMemory.InMemoryQueryBus;
import com.rallibau.shared.infraestructure.bus.query.rabbitmq.RabbitQueryBus;
import com.rallibau.shared.infraestructure.bus.shared.rabbitmq.RabbitMqPublisher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {
    public static final String RABBIT = "rabbit";

    private final RabbitMqPublisher rabbitMqPublisher;
    private final ApplicationEventPublisher publisher;
    private final CommandHandlersInformation commandInformation;
    private final QueryHandlersInformation queryHandlersInformation;
    private final ApplicationContext context;

    @Value("${mq.events.provider}")
    private String MQ_EVENTS_PROVIDER;

    @Value("${mq.commands.provider}")
    private String MQ_COMMANDS_PROVIDER;

    @Value("${mq.query.provider}")
    private String MQ_QUERY_PROVIDER;

    public AppConfig(RabbitMqPublisher rabbitMqPublisher,
                     ApplicationEventPublisher publisher,
                     CommandHandlersInformation commandInformation,
                     QueryHandlersInformation queryHandlersInformation, ApplicationContext context) {
        this.rabbitMqPublisher = rabbitMqPublisher;
        this.publisher = publisher;
        this.commandInformation = commandInformation;
        this.queryHandlersInformation = queryHandlersInformation;
        this.context = context;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "OPTIONS");
            }
        };
    }


    @Bean
    public EventBus eventBus() {
        if (RABBIT.equals(MQ_EVENTS_PROVIDER)) {
            return new RabbitMqEventBus(rabbitMqPublisher);
        }
        return new SpringApplicationEventBus(publisher);
    }

    @Bean
    public CommandBus commandBus() {
        if (RABBIT.equals(MQ_COMMANDS_PROVIDER)) {
            return new RabbitCommandBus(rabbitMqPublisher);
        }
        return new InMemoryCommandBus(commandInformation, context);
    }

    @Bean
    public QueryBus queryBus() {
        if (RABBIT.equals(MQ_QUERY_PROVIDER)) {
            return new RabbitQueryBus(rabbitMqPublisher);
        }
        return new InMemoryQueryBus(queryHandlersInformation, context);
    }
}
