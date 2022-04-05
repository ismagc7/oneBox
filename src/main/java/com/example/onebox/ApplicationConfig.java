package com.example.onebox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	public DBInitializer dbInitializer() {
		return new DBInitializer();
	}
}
