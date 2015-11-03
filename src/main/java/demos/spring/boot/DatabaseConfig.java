package demos.spring.boot;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
public class DatabaseConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");
	    dataSource.setUrl("jdbc:postgresql://newleave-db.cedv7lo2nhz5.us-east-1.rds.amazonaws.com:5432/newleave");
	    dataSource.setUsername("jamesmiller");
	    dataSource.setPassword("123123123");
	    return dataSource;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource);
		    
		// Classpath scanning of @Component, @Service, etc annotated class
		entityManagerFactory.setPackagesToScan("demos.spring.boot");
		
		// Vendor adapter
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

		// Hibernate properties
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		additionalProperties.put("hibernate.show_sql", true);
		additionalProperties.put("hibernate.hbm2ddl.auto", "create-drop");
		entityManagerFactory.setJpaProperties(additionalProperties);

		return entityManagerFactory;
	}

}
