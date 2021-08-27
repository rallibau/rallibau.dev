package com.rallibau.apps.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-${env:local}.properties")
public class ConfigurationProvider {
}
