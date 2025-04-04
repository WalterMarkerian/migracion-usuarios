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
//        basePackages = "com.sportclub.migracion_usuarios.db2",
//        entityManagerFactoryRef = "entityManagerFactoryDb2",
//        transactionManagerRef = "transactionManagerDb2"
//)
//public class DataSourceConfigDb2 {
//
//    @Bean(name = "dataSourceDb2")
//    @ConfigurationProperties(prefix = "spring.datasource.db2")
//    public DataSource dataSourceDb2() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "entityManagerFactoryDb2")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryDb2(
//            @Qualifier("dataSourceDb2") DataSource dataSource) {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        return new EntityManagerFactoryBuilder(vendorAdapter, new HashMap<>(), null)
//                .dataSource(dataSource)
//                .packages("com.sportclub.migracion_usuarios.db2")
//                .persistenceUnit("db2")
//                .build();
//    }
//
//    @Bean(name = "transactionManagerDb2")
//    public PlatformTransactionManager transactionManagerDb2(
//            @Qualifier("entityManagerFactoryDb2") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}
