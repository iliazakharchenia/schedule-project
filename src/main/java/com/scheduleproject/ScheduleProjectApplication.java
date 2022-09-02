package com.scheduleproject;

import com.scheduleproject.containers.DockerPostgresContainer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ScheduleProjectApplication {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		DockerPostgresContainer postgresContainer = new DockerPostgresContainer();
		postgresContainer.start();
		SpringApplication.run(ScheduleProjectApplication.class, args);
	}

}
