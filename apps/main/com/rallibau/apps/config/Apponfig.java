package com.rallibau.apps.config;

import com.rallibau.shared.domain.bus.command.CommandBus;
import com.rallibau.shared.domain.bus.event.EventBus;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqEventBus;
import com.rallibau.shared.infraestructure.bus.event.rabbitmq.RabbitMqPublisher;
import com.rallibau.shared.infraestructure.bus.event.spring.SpringApplicationEventBus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Apponfig {
    public static final String RABBIT = "rabbit";
    private final CommandBus bus;

    private final RabbitMqPublisher rabbitMqPublisher;
    private final ApplicationEventPublisher publisher;

    @Value("${mq.provider}")
    private String MQ_PROVIDER;

    public Apponfig(CommandBus bus, RabbitMqPublisher rabbitMqPublisher, ApplicationEventPublisher publisher) {
        this.bus = bus;
        this.rabbitMqPublisher = rabbitMqPublisher;
        this.publisher = publisher;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT");
            }
        };
    }

    @Bean
    public EventBus eventBus(){
        if(RABBIT.equals(MQ_PROVIDER)){
            return new RabbitMqEventBus(rabbitMqPublisher);
        }
        return new SpringApplicationEventBus(publisher);

    }
}
