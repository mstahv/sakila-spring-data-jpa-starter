package com.example.demo;

import com.example.demo.entities.Customer;
import com.example.demo.repositories.CustomerInfoRepository;
import com.example.demo.repositories.CustomerRepository;
import com.example.demo.repositories.SalesByStoreRepository;
import com.example.demo.entities.views.CustomerInfo;
import com.example.demo.entities.views.SalesByStore;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.Metamodel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	void listAllEntities(@Autowired EntityManagerFactory entityManagerFactory) {

		Metamodel metamodel = entityManagerFactory.getMetamodel();
		Set<EntityType<?>> entities = metamodel.getEntities();
		for (EntityType<?> entityType : entities) {
			Class<?> javaType = entityType.getJavaType();
			System.out.println("Entity: " + entityType.getName());
			Set<Attribute<?, ?>> attributes = (Set<Attribute<?, ?>>) entityType.getAttributes();
			for (Attribute<?, ?> attribute : attributes) {
				Attribute.PersistentAttributeType persistentAttributeType = attribute.getPersistentAttributeType();
				System.out.println("\t" + attribute.getName() + " ("+ attribute.getPersistentAttributeType() + " / " + attribute.getJavaType() + ")");
			}
		}
	}

	@Test
	void testCustomerRepository(@Autowired CustomerRepository customerRepository) {
		long counted = customerRepository.count();
		Assertions.assertEquals(599, counted);

		Customer customer = customerRepository.findById(1L).get();
		Assertions.assertEquals("MARY", customer.getFirstName());
	}

	@Test
	void testEntityEquals(@Autowired CustomerRepository customerRepository) {
		// TODO implement suitable equals and hashCode in an AbstractEntity class
		Customer customer1 = customerRepository.findById(1L).get();
		Customer customer2 = customerRepository.findById(1L).get();
		// Assertions.assertEquals(customer1, customer2);
	}

	@Test
	void testSalesByStoreView(@Autowired SalesByStoreRepository salesByStoreRepository) {
		List<SalesByStore> all = salesByStoreRepository.findAll();
		for (SalesByStore salesByStore : all) {
			System.out.println(salesByStore.getStore() + " | " + salesByStore.getTotalSales());
		}
		Assertions.assertEquals(2, all.size());
	}

	@Test
	void testCustomerInfoView(@Autowired CustomerInfoRepository customerInfoRepository) {
		long count = customerInfoRepository.count();
		Assertions.assertEquals(599, count);
		// findByCustomerId should joining actual entities to customer and store (& store.address)
		CustomerInfo info = customerInfoRepository.findByCustomerId(1L);
		String email = info.getCustomer().getEmail();
		Assertions.assertNotNull(email);
		System.out.println(info.getName() + " Store address: " + info.getStore().getAddress().getAddress() + " Customer email:" + email);
	}

}
