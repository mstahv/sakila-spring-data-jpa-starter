package com.example.demo.vaadinui;

import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.vaadin.firitin.components.grid.VGrid;

import java.util.stream.Stream;

public class SpringDataGrid<T> extends VGrid<T> {

    interface RowFetchCallback<T> extends SerializableFunction<Pageable, Stream<T>> {

    }

    public SpringDataGrid(Class<T> beanType) {
        super(beanType);
    }

    void setRows(RowFetchCallback<T> binding) {
        // convert the Vaadin Query to a Spring Pageable
        setItems(query -> {
            PageRequest springPageRequest = VaadinSpringDataHelpers.toSpringPageRequest(query);
            return binding.apply(springPageRequest);
        });
    }

}
