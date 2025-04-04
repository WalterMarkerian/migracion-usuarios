package com.sportclub.migracion_usuarios.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {
                "com.sportclub.migracion_usuarios.sede.infrastructure.repository",
                "com.sportclub.migracion_usuarios.user.infrastructure.repository"
        },
        entityManagerFactoryRef = "sharedEntityManagerFactory",
        transactionManagerRef = "sharedTransactionManager"
)
public class SharedJpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean sharedEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("sourceDataSource") DataSource dataSource) { // Usamos source como default

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.show_sql", "true");

        return builder
                .dataSource(dataSource) // Datasource por defecto
                .packages(
                        "com.sportclub.migracion_usuarios.sede.domain.entity",
                        "com.sportclub.migracion_usuarios.user.domain.entity"
                )
                .persistenceUnit("sharedPU")
                .properties(properties)
                .build();
    }

    @Bean
    public PlatformTransactionManager sharedTransactionManager(
            @Qualifier("sharedEntityManagerFactory") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}