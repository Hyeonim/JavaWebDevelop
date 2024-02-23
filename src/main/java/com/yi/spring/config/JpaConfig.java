package com.yi.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Properties;
@Configuration
public class JpaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private  String password;
    ////////////////
    @Value("${spring.jpa.database}")
    private  String database;

    @Value("${spring.jpa.database-platform}")
    private  String databasePlatForm;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private  String ddlAuto;

    @Value("${spring.jpa.generate-ddl}")
    private  String ddl;

    @Value("${spring.jpa.show-sql}")
    private  String showSql;

    @Value("${spring.jpa.properties.hibernate.format_sql}")
    private  String formatSql;

    @Value("${spring.jpa.properties.hibernate.enable_lazy_load_no_trans}")
    private  String lazyTrans;
//
//    @Bean
//    public HikariDataSource dataSource() {
//        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setDriverClassName(driverClassName); // MySQL 드라이버 지정
//        dataSource.setJdbcUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource());
//        em.setPackagesToScan("com.yi.spring.entity"); // Entity 위치 지정
//        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        em.setJpaProperties(jpaProperties());
//        return em;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//        return transactionManager;
//    }
//
//    private Properties jpaProperties() {
//        Properties properties = new Properties();
//        properties.setProperty("hibernate.dialect", databasePlatForm);
//        properties.setProperty("hibernate.ddl-auto", ddlAuto);
//        properties.setProperty("hibernate.generate_ddl", ddl);
//        properties.setProperty("hibernate.show_sql", showSql);
//        properties.setProperty("hibernate.format_sql", formatSql);
//        properties.setProperty("hibernate.enable_lazy_load_no_trans", lazyTrans);
//        return properties;
//    }

}
