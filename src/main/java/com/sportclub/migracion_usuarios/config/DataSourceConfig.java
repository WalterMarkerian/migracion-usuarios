package com.sportclub.migracion_usuarios.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "sourceDataSource")
    @ConfigurationProperties("spring.datasource.source")
    public DataSource sourceDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class) // Especifica explícitamente Hikari
                .build();
    }

    @Bean(name = "targetDataSource")
    @ConfigurationProperties("spring.datasource.target")
    public DataSource targetDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class) // Especifica explícitamente Hikari
                .build();
    }
}