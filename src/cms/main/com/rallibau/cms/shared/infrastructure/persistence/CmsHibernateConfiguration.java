package com.rallibau.cms.shared.infrastructure.persistence;

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
public class CmsHibernateConfiguration {
    @Value("${cms.database.host}")
    private String DATABASE_HOST;
    @Value("${cms.database.port}")
    private String DATABASE_PORT;
    @Value("${cms.database.name}")
    private String DATABASE_NAME;
    @Value("${cms.database.user}")
    private String DATABASE_USER;
    @Value("${cms.database.password}")
    private String DATABASE_PASSWORD;
    @Value("${cms.database.dialect}")
    private String DIALECT;
    @Value("${cms.database.driver}")
    private String DRIVER;
    @Value("${cms.database.url}")
    private String DATABASE_URL;

    private final HibernateConfigurationFactory factory;
    @Value("${cms.database.name}")
    private String CONTEXT_NAME;

    public CmsHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("cms-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("cms-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException {
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("cms-data_source")
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
