package com.example.demo;

import com.example.demo.sakilaentities.Customer;
import com.example.demo.springdata.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

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
	
	@Test
	void testCustomerRepository(@Autowired CustomerRepository customerRepository) {
		long counted = customerRepository.count();
		Assertions.assertEquals(599, counted);

		Customer customer = customerRepository.findById(1L).get();
		Assertions.assertEquals("MARY", customer.getFirstName());
	}

}
