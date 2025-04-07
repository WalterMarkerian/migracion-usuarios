package com.sportclub.migracion_usuarios.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.sportclub.migracion_usuarios.sede.infrastructure.repository",
                "com.sportclub.migracion_usuarios.user.infrastructure.repository"
        },
        entityManagerFactoryRef = "entityManagerFactoryDb1",
        transactionManagerRef = "transactionManagerDb1"
)
public class DataSourceConfigDb1 {

    @Primary
    @Bean(name = "sourceDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.source-db")
    public DataSource dataSourceDb1() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryDb1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDb1(
            EntityManagerFactoryBuilder builder,
            @Qualifier("sourceDataSource") DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages(
                        "com.sportclub.migracion_usuarios.sede.domain.entity",
                        "com.sportclub.migracion_usuarios.user.domain.entity"
                )
                .persistenceUnit("source")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerDb1")
    public PlatformTransactionManager transactionManagerDb1(
            @Qualifier("entityManagerFactoryDb1") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
