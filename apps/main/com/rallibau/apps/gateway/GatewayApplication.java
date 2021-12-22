package com.rallibau.apps.gateway;

import com.rallibau.shared.domain.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
        value = {"com.rallibau.apps","com.rallibau.shared"}
)
public class GatewayApplication {


    public static HashMap<String, Class<?>> commands() {
        return new HashMap<>() {};
    }
}
