package com.rallibau.bpm.shared.infrastructure.persistence;

import com.rallibau.shared.infraestructure.persistence.hibernate.HibernateConfigurationFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
public class BpmHibernateConfiguration {
    @Value("${bpm.database.host}")
    private String DATABASE_HOST;
    @Value("${bpm.database.port}")
    private String DATABASE_PORT;
    @Value("${bpm.database.name}")
    private String DATABASE_NAME;
    @Value("${bpm.database.user}")
    private String DATABASE_USER;
    @Value("${bpm.database.password}")
    private String DATABASE_PASSWORD;
    @Value("${bpm.database.dialect}")
    private String DIALECT;
    @Value("${bpm.database.driver}")
    private String DRIVER;
    @Value("${bpm.database.url}")
    private String DATABASE_URL;

    private final HibernateConfigurationFactory factory;
    @Value("${bpm.database.name}")
    private String CONTEXT_NAME;

    public BpmHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("bpm-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException{
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("bpm-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException{
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("bpm-data_source")
    public DataSource dataSource() throws IOException {
        return factory.dataSource(
                DATABASE_HOST,
                Integer.parseInt(DATABASE_PORT),
                DATABASE_NAME,
                DATABASE_USER,
                DATABASE_PASSWORD,
                DIALECT,
                DRIVER,
                DATABASE_URL
        );
    }
}
