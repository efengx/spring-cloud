package com.yangst.cloud.demo.config.datasource.jpa.config;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "slaveEntityManagerFactory",
                       /*transactionManagerRef = "slaveTransactionManager",*/
                       basePackages = {"cloud.yangst.cloud.demo.repository.slave"},//设置dao（repo）所在位置
                       repositoryBaseClass = BaseSimpleJpaRepositoryEx.class)//jpa增強配置

public class SlaveRepositoryConfig {
	
    @Autowired
    private JpaProperties jpaProperties;

    @Autowired
    @Qualifier("slaveDataSource")
    private DataSource slaveDataSource;

    @Bean(name = "slaveEntityManager")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return slaveEntityManagerFactory(builder).getObject().createEntityManager();
    }

    @Bean(name = "slaveEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean slaveEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(slaveDataSource)
                .properties(getVendorProperties())
                .packages("cloud.service.payment.entity.po")//设置实体类所在位置
                .persistenceUnit("slavePersistenceUnit")
                .build(); 
    }

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    /*
     * 事务管理
     */
//    @Bean(name = "slaveTransactionManager")
//    PlatformTransactionManager slaveTransactionManager(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(slaveEntityManagerFactory(builder).getObject());
//    }

}