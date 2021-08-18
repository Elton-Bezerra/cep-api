package br.com.boroco.cepapi.adapters.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	public DataSource getDataSource() {
		return DataSourceBuilder
			.create()
			.driverClassName("org.h2.Driver")
			.url("jdbc:h2:file:./src/main/resources/cepdb")
			.username("sa")
			.password("")
			.build();
	}
}
