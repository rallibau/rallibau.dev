package com.inetum.livetooling.apps.tagReader;

import com.inetum.livetooling.shared.domain.Service;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import java.util.HashMap;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
@ComponentScan(
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Service.class),
		value = {"com.inetum.livetooling.shared","com.inetum.livetooling.apps.tagReader","com.inetum.livetooling.lista"}
)
public class TagReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TagReaderApplication.class, args);

	}

	public static HashMap<String, Class<?>> commands() {
		return new HashMap<String, Class<?>>() {{
		}};
	}

}
