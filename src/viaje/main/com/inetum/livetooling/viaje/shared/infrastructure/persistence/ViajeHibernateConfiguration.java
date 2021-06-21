package com.inetum.livetooling.viaje.shared.infrastructure.persistence;

import com.inetum.livetooling.shared.infraestructure.hibernate.HibernateConfigurationFactory;
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
public class ViajeHibernateConfiguration {
    @Value("${viaje.database.host}")
    private String DATABASE_HOST;
    @Value("${viaje.database.port}")
    private String DATABASE_PORT;
    @Value("${viaje.database.name}")
    private String DATABASE_NAME;
    @Value("${viaje.database.user}")
    private String DATABASE_USER;
    @Value("${viaje.database.password}")
    private String DATABASE_PASSWORD;
    @Value("${viaje.database.dialect}")
    private String DIALECT;
    @Value("${viaje.database.driver}")
    private String DRIVER;
    @Value("${viaje.database.url}")
    private String DATABASE_URL;

    private final HibernateConfigurationFactory factory;
    @Value("${viaje.database.name}")
    private String CONTEXT_NAME;

    public ViajeHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("viaje-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException{
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("viaje-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException{
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("viaje-data_source")
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
