package com.psr.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.psr")
@EntityScan("com.psr.models")
@EnableJpaRepositories("com.psr.repository")
public class ProyectoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApiApplication.class, args);
	}

}
