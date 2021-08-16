package com.inetum.livetooling.saldo.shared.infrastructure.persistence;

import com.rallibau.shared.infraestructure.hibernate.HibernateConfigurationFactory;
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
public class SaldoHibernateConfiguration {
    @Value("${saldo.database.host}")
    private String DATABASE_HOST;
    @Value("${saldo.database.port}")
    private String DATABASE_PORT;
    @Value("${saldo.database.name}")
    private String DATABASE_NAME;
    @Value("${saldo.database.user}")
    private String DATABASE_USER;
    @Value("${saldo.database.password}")
    private String DATABASE_PASSWORD;
    @Value("${saldo.database.dialect}")
    private String DIALECT;
    @Value("${saldo.database.driver}")
    private String DRIVER;
    @Value("${saldo.database.url}")
    private String DATABASE_URL;

    private final HibernateConfigurationFactory factory;
    @Value("${saldo.database.name}")
    private String CONTEXT_NAME;

    public SaldoHibernateConfiguration(HibernateConfigurationFactory factory) {
        this.factory = factory;
    }

    @Bean("facturacion-transaction_manager")
    public PlatformTransactionManager hibernateTransactionManager() throws IOException{
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean("facturacion-session_factory")
    public LocalSessionFactoryBean sessionFactory() throws IOException{
        return factory.sessionFactory(CONTEXT_NAME, dataSource());
    }

    @Bean("facturacion-data_source")
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
