package com.rallibau.apps.bpm;

import com.rallibau.shared.domain.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"com.rallibau.apps.commons","com.rallibau.shared", "com.rallibau.bpm", "com.rallibau.apps.bpm"}
)
public class BpmApplication {


    public static HashMap<String, Class<?>> commands() {
        return new HashMap<String, Class<?>>() {{
            //put("domain-events:mysql:consume", ConsumeMySqlDomainEventsCommand.class);
            //put("domain-events:rabbitmq:consume", ConsumeRabbitMqDomainEventsCommand.class);
        }};
    }
}
