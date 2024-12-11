package com.example.demo.repositories;

import com.example.demo.entities.views.SalesByStore;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO consider making a read-only repository
public interface SalesByStoreRepository extends JpaRepository<SalesByStore, String> {
}
