package com.ejls.service.user.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "baseEntityManagerFactory", 
    basePackages = { "com.ejls.service.user.repository.base"}
)
public class DBBaseConfig {
    
    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "datasource.base")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean(name = "baseEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean baseEntityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier("baseDataSource") DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.ejls.service.user.domain.base").persistenceUnit("base").build();
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
			@Qualifier("baseEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
