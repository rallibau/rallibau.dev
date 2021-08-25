package com.rallibau.apps.bpm.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@PropertySource("classpath:application-${env}.properties")
public class ConfigurationProvider {
}
