package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.Map;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	void contextLoads() throws SQLException {
		Map<String, Object> row = jdbcTemplate.queryForMap("SELECT * FROM actor WHERE actor_id = 1");
		Assertions.assertEquals("PENELOPE", row.get("first_name"));
	}

}
