package com.inetum.livetooling.lista.shared.infrastructure.persistence;

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
public class ListaHibernateConfiguration {
    @Value("${lista.database.host}")
    private String DATABASE_HOST;
    @Value("${lista.database.port}")
    private String DATABASE_PORT;
    @Value("${lista.database.name}")
    private String DATABASE_NAME;
    @Value("${lista.database.user}")
    private String DATABASE_USER;
    @Value("${lista.database.password}")
    private String DATABASE_PASSWORD;
    @Value("${lista.database.dialect}")
    private String DIALECT;
    @Value("${lista.database.driver}")
    private String DRIVER;
    @Value("${lista.database.url}")
    private String DATABASE_URL;

    private final HibernateConfigurationFactory factory;
    @Value("${lista.database.name}")
    private String CONTEXT_NAME;

    public ListaHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("lista-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException{
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("lista-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException{
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("lista-data_source")
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
