package com.idexcel.adminservice;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class AdminServiceConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
