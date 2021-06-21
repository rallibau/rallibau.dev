package com.inetum.livetooling.apps.livetooling.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ConfigurationProvider {
}
