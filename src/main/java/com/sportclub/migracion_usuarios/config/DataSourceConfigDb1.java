//package com.sportclub.migracion_usuarios.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.sportclub.migracion_usuarios.db1", // repositorios DB1
//        entityManagerFactoryRef = "entityManagerFactoryDb1",
//        transactionManagerRef = "transactionManagerDb1"
//)
//public class DataSourceConfigDb1 {
//
//    @Bean(name = "dataSourceDb1")
//    @ConfigurationProperties(prefix = "spring.datasource.db1")
//    public DataSource dataSourceDb1() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "entityManagerFactoryDb1")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDb1(
//            @Qualifier("dataSourceDb1") DataSource dataSource) {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        return new EntityManagerFactoryBuilder(vendorAdapter, new HashMap<>(), null)
//                .dataSource(dataSource)
//                .packages("com.sportclub.migracion_usuarios.db1") // entidades DB1
//                .persistenceUnit("db1")
//                .build();
//    }
//
//    @Bean(name = "transactionManagerDb1")
//    public PlatformTransactionManager transactionManagerDb1(
//            @Qualifier("entityManagerFactoryDb1") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}
