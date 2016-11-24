package com.aiconoa.slideshow.config;

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

import com.aiconoa.slideshow.entity.Slideshow;
import com.aiconoa.slideshow.repository.SlideshowRepository;

@Configuration
@EnableJpaRepositories(
		basePackageClasses= SlideshowRepository.class,
		entityManagerFactoryRef = "slideshowEntityManagerFactory",
		transactionManagerRef = "slideshowTransactionManager")
public class SlideshowConfig {

	@Bean
	PlatformTransactionManager slideshowTransactionManager() {
		return new JpaTransactionManager(slideshowEntityManagerFactory().getObject());
	}
	
	@Bean
	LocalContainerEntityManagerFactoryBean slideshowEntityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(false);

		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		factoryBean.setDataSource(primaryDataSource());
		factoryBean.setJpaVendorAdapter(vendorAdapter);
		factoryBean.setPackagesToScan(
				Slideshow.class.getPackage().getName(),
				SlideshowRepository.class.getPackage().getName());

		return factoryBean;
	}
	
	@Bean
	@ConfigurationProperties(prefix="datasource.slideshow")
	public DataSource primaryDataSource() {
	    return DataSourceBuilder.create().build();
	}
	
}
