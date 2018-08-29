package org.soframel.tests.flowable.spring;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * Storage configuration.
 * 
 * Reads properties from /persistence-storage.properties
 *
 * 
 * 
 * Expect a data source to be provided.
 * 
 * - Unit test it uses an in-memory H2.
 * 
 * - On Websphere it will be provided the managed DataSource.
 *
 * 
 * 
 * @author Alexandre Grison (eqk83)
 * 
 */
@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/persistence-storage.properties")
public class StorageConfig {
	@Autowired
	private Environment env;
	@Autowired
	DataSource dataSource;

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryMock() {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		fillEntityManagerFactory(em);
		em.setDataSource(dataSource);
		return em;
	}

	private void fillEntityManagerFactory(LocalContainerEntityManagerFactoryBean em) {
		em.setPackagesToScan("org.soframel.tests.flowable.spring");
		final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
	}

	private Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		return hibernateProperties;
	}
}