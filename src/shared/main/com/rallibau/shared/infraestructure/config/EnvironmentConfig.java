package com.rallibau.shared.infraestructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class EnvironmentConfig implements WebMvcConfigurer {
    ResourceLoader resourceLoader;

    public EnvironmentConfig(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public Dotenv dotenv() {
        Resource resource = resourceLoader.getResource("classpath:/.env.local");

        return Dotenv
            .configure()
            .directory("/")
            .filename(resource.exists() ? ".env.local" : ".env")
            .load();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
