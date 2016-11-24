package com.aiconoa.sakila.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.aiconoa.sakila.entity.Film;
import com.aiconoa.sakila.repository.FilmRepository;

@Configuration
@EnableJpaRepositories(
		basePackageClasses= FilmRepository.class,
		entityManagerFactoryRef = "sakilaEntityManagerFactory",
		transactionManagerRef = "sakilaTransactionManager")
public class SakilaConfig {

	@Bean
	PlatformTransactionManager sakilaTransactionManager() {
		return new JpaTransactionManager(sakilaEntityManagerFactory().getObject());
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean sakilaEntityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(sakilaDataSource());
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setPackagesToScan(
				Film.class.getPackage().getName(),
				FilmRepository.class.getPackage().getName());

		return factoryBean;
	}
	
	@Bean
	@ConfigurationProperties(prefix="datasource.sakila")
	public DataSource sakilaDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
}
