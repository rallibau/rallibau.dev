package com.rallibau.acl.shared.infrastructure.persistence;

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
public class AclHibernateConfiguration {
    @Value("${acl.database.host}")
    private String DATABASE_HOST;
    @Value("${acl.database.port}")
    private String DATABASE_PORT;
    @Value("${acl.database.name}")
    private String DATABASE_NAME;
    @Value("${acl.database.user}")
    private String DATABASE_USER;
    @Value("${acl.database.password}")
    private String DATABASE_PASSWORD;
    @Value("${acl.database.dialect}")
    private String DIALECT;
    @Value("${acl.database.driver}")
    private String DRIVER;
    @Value("${acl.database.url}")
    private String DATABASE_URL;

    private final HibernateConfigurationFactory factory;
    @Value("${acl.database.name}")
    private String CONTEXT_NAME;

    public AclHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("acl-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("acl-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("acl-data_source")
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
