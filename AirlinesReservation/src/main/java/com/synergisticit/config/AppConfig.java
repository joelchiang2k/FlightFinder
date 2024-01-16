package com.synergisticit.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;



@Configuration
public class AppConfig {
//	@Autowired DataSource dataSource;
	
	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setPackagesToScan("com.synergisticit.domain");
		entityManager.setDataSource(dataSource());
		entityManager.setJpaProperties(properties());
		entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		return entityManager;
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("60Alif37ea");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/airlines?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
		return dataSource;
	}
	
	public Properties properties() {
		Properties p = new Properties();
		p.setProperty("hibernate.hbm2ddl.auto", "update");
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return p;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setAnnotatedPackages("com.synergisticit.domain");
		sessionFactory.setHibernateProperties(properties());
		
		return sessionFactory;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		 viewResolver.setPrefix("WEB-INF/jsp/");
		 viewResolver.setSuffix(".jsp");
		return  viewResolver;
	}
	
//	@Bean
//	NoOpPasswordEncoder noOpPasswordEncoder() {
//		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
//	
//	@Bean
//	BCryptPasswordEncoder bCryptPasswordEncoder() {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encrypted = encoder.encode("joel");
//		System.out.println("encrypted:" + encrypted);
//		return encoder;
//	}
}
