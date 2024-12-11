package com.example.demo.entities.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sales_by_store")
public class SalesByStore {

    @Id
    @Column(name = "store")
    private String store;

    private String manager;

    @Column(name = "total_sales")
    private String totalSales;

    public String getStore() {
        return store;
    }

    public String getManager() {
        return manager;
    }

    public String getTotalSales() {
        return totalSales;
    }
}
