package com.product_inventory.application.config;

import java.util.Properties;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataBaseConfig {
	
		@Bean
		public DataSource dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName(env.getProperty("db.driver"));
		    dataSource.setUrl(env.getProperty("db.url"));
		    dataSource.setUsername(env.getProperty("db.username"));
		    dataSource.setPassword(env.getProperty("db.password"));
		    return dataSource;
		}
		
		@Bean(name="product_inventory")
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
			LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactory.setDataSource(dataSource);
			entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));
			
			// Vendor adapter
		    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
			
			// Hibernate properties
		    Properties additionalProperties = new Properties();
		    additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		    additionalProperties.put("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
		    additionalProperties.put("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
		    entityManagerFactory.setJpaProperties(additionalProperties);
		    
		    return entityManagerFactory;
		}
		
		/**
		   * Declare the transaction manager.
		   */
		  @Bean
		  public JpaTransactionManager transactionManager() {
		    JpaTransactionManager transactionManager = 
		        new JpaTransactionManager();
		    transactionManager.setEntityManagerFactory(
		        entityManagerFactory.getObject());
		    return transactionManager;
		  }
		  
		  /**
		   * PersistenceExceptionTranslationPostProcessor is a bean post processor
		   * which adds an advisor to any bean annotated with Repository so that any
		   * platform-specific exceptions are caught and then rethrown as one
		   * Spring's unchecked data access exceptions (i.e. a subclass of 
		   * DataAccessException).
		   */
		  @Bean
		  public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		    return new PersistenceExceptionTranslationPostProcessor();
		  }
		  
	
		@Autowired
		private Environment env;
		
		@Autowired
		private DataSource dataSource;
		
		@Autowired
		private LocalContainerEntityManagerFactoryBean entityManagerFactory;
}
