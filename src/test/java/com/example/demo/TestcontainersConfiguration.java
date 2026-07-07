package com.example.demo;

import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	MariaDBContainer<?> mariadbContainer() {
		DockerImageName myImage = DockerImageName.parse("sakiladb/mariadb:11")
				.asCompatibleSubstituteFor("mariadb");
		return new MariaDBContainer<>(myImage)
				.withReuse(true)
				.withDatabaseName("sakila")
				.withUsername("sakila")
				.withPassword("p_ssW0rd");
	}
}
