package com.example.demo.vaadinui;

import com.example.demo.sakilaentities.Customer;
import com.example.demo.springdata.CustomerRepository;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.vaadin.firitin.components.grid.VGrid;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;

@Route(layout = TopLayout.class)
public class CustomerView extends VVerticalLayout {
    public CustomerView(CustomerRepository customerRepository) {

        add(new CustomerGrid(customerRepository));
    }

    public static class CustomerGrid extends VGrid<Customer> {

        public CustomerGrid(CustomerRepository customerRepository) {
            super(Customer.class);
            getColumns().forEach(column -> column.setAutoWidth(true));
            setItems(VaadinSpringDataHelpers.fromPagingRepository(customerRepository));
        }
    }
}
