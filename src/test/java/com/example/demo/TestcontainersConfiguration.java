package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@TestConfiguration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		DockerImageName myImage = DockerImageName.parse("sakiladb/postgres:15")
				.asCompatibleSubstituteFor("postgres");

		var c = new PostgreSQLContainer<>(myImage)
				.withReuse(true)
				.withDatabaseName("sakila")
				.withUsername("sakila")
				.withPassword("p_ssW0rd");
		// TODO Figure out if this is a bug in TestContainers or not? The default seems to be waiting for the message 2 times although it seems to be coming out only once 😬
		c.setWaitStrategy(new LogMessageWaitStrategy().withRegEx(".*database system is ready to accept connections.*\\s").withTimes(1).withStartupTimeout(Duration.of(60L, ChronoUnit.SECONDS)));
		return c;
	}
}
