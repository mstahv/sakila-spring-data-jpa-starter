package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class TestDemoApplication {

	public static void main(String[] args) {
		System.setProperty("vaadin.vaadin.frontend.frontend.folder", "./target/frontend");
		SpringApplication.from(DemoApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
