package com.shopping.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfiguration {

	   @Bean
	   public LocalSessionFactoryBean getSessionFactory() {
	      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	      sessionFactory.setDataSource(getDataSource());
	      sessionFactory.setPackagesToScan(new String[] { "com.shopping.entity" });
	      sessionFactory.setHibernateProperties(hibernateProperties());
	      return sessionFactory;
	   }

	   @Bean
	   public DataSource getDataSource() {
	      BasicDataSource dataSource = new BasicDataSource();
	      dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	      dataSource.setUrl("jdbc:mysql://localhost:3306/shoppingcart_schema?useSSL=false");
	      dataSource.setUsername("root");
	      dataSource.setPassword("admin123");
	      return dataSource;
	   }

	   @Bean
	   public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
	      HibernateTransactionManager txManager = new HibernateTransactionManager();
	      txManager.setSessionFactory(sessionFactory);
	      return txManager;
	   }


	   Properties hibernateProperties() {
	      return new Properties() {
	        private static final long serialVersionUID = 1L;
	        {
	            setProperty("connection.pool_size", "11");
	            setProperty("dialect", "org.hibernate.dialect.MySQL57Dialect");
	            setProperty("show_sql", "true");
	            setProperty("current_session_context_class", "thread");
	         }
	      };
	   }
}
