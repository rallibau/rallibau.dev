package com.inetum.livetooling.apps.gestionViaje;

import com.inetum.livetooling.apps.gestionViaje.command.ConsumeRabbitMqDomainEventsCommand;
import com.inetum.livetooling.shared.domain.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"com.inetum.livetooling.shared", "com.inetum.livetooling.viaje", "com.inetum.livetooling.apps.gestionViaje"}
)
public class GestionViajeApplication {
    public static HashMap<String, Class<?>> commands() {
        return new HashMap<String, Class<?>>() {{
            put("domain-events:rabbitmq:consume", ConsumeRabbitMqDomainEventsCommand.class);
        }};
    }
}
