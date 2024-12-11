package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
@EnableTransactionManagement
@EnableJpaRepositories
@EntityScan
class TestcontainersConfiguration {

	@Bean
	@ServiceConnection
	MySQLContainer<?> mysqlContainer() {
		DockerImageName myImage = DockerImageName.parse("sakiladb/mysql:8")
				.asCompatibleSubstituteFor("mysql");
		return new MySQLContainer<>(myImage)
				.withReuse(true)
				.withDatabaseName("sakila")
				.withUsername("sakila")
				.withPassword("p_ssW0rd");
	}
}
