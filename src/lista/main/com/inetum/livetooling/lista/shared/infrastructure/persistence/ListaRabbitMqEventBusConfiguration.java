package com.inetum.livetooling.lista.shared.infrastructure.persistence;

import com.inetum.livetooling.shared.infraestructure.bus.event.rabbitmq.RabbitMqEventBus;
import com.inetum.livetooling.shared.infraestructure.bus.event.rabbitmq.RabbitMqPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListaRabbitMqEventBusConfiguration {
    private final RabbitMqPublisher publisher;

    public ListaRabbitMqEventBusConfiguration(
            RabbitMqPublisher publisher
    ) {
        this.publisher = publisher;
    }

    @Bean
    public RabbitMqEventBus moocRabbitMqEventBus() {
        return new RabbitMqEventBus(publisher);
    }
}
