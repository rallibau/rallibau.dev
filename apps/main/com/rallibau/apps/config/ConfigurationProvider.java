package com.rallibau.apps.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-${env}.properties")
public class ConfigurationProvider {
}
