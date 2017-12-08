package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.niit" })
@EnableTransactionManagement
public class DBconfig {

	/*
	 * Data Base Configurations
	 */

	/*
	 * DataSource Bean
	 */
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getDataSource() {

		System.out.println("Data Source Method");
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		/*
		 * Giving Data Source Configuration
		 */
		driverManagerDataSource.setDriverClassName("org.h2.Driver");
		driverManagerDataSource.setUrl("jdbc:h2:tcp://localhost/~/test3");
		driverManagerDataSource.setUsername("Blog");
		driverManagerDataSource.setPassword("prakash");
		System.out.println("Data Source Created");
		return driverManagerDataSource;
	}

	/*
	 * For Hibernate Properties
	 */
	public Properties getHibernateProperties() {

		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		// properties.put("hibernate.connection.pool_size", 5);
		// <property name="hibernate.connection.pool_size">5</property>
		System.out.println("Hibernate Proprty created");
		
		return properties;
	}

	/*
	 * Session Factory Bean
	 */
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);

		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.scanPackages("com.niit.model");
		System.out.println("SessionFactory Bean Created");
		return sessionBuilder.buildSessionFactory();
	}

	/*
	 * Hibernate Transaction Manager
	 */
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory) {

		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

}
