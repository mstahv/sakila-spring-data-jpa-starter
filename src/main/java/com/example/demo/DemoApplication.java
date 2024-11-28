package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// TODO either configure your "production database" or start the TestDemoApplication class from the test sources
		// that automatically starts a Docker container with DB configured for testing and development
		SpringApplication.run(DemoApplication.class, args);
	}

}
