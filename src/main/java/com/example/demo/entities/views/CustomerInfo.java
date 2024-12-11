package com.example.demo.entities.views;

import com.example.demo.entities.Customer;
import com.example.demo.entities.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_list")
@NamedEntityGraph(name = "storeAndCustomer", attributeNodes = {
        @NamedAttributeNode("customer"),
        @NamedAttributeNode(value = "store", subgraph = "subgraph.store")
}, subgraphs = {
        @NamedSubgraph(name = "subgraph.store", attributeNodes = {
                @NamedAttributeNode(value = "address")
        })
})
public class CustomerInfo {

    @Id
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "SID")
    private Long storeId;

    @ManyToOne
    @JoinColumn(name = "ID", updatable = false, insertable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "SID", updatable = false, insertable = false)
    private Store store;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "zip code")
    private String zipCode;

    @Column(name = "phone")
    private String phone;

    private String city;
    private String country;
    private String notes;


    public Long getId() {
        return id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Store getStore() {
        return store;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getNotes() {
        return notes;
    }
}
