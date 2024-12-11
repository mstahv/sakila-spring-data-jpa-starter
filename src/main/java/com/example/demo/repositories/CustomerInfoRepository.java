package com.example.demo.repositories;

import com.example.demo.entities.views.CustomerInfo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO consider making this a read-only repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, Long> {

    @EntityGraph(value = "storeAndCustomer", type = EntityGraph.EntityGraphType.LOAD)
    CustomerInfo findByCustomerId(Long id);
}
