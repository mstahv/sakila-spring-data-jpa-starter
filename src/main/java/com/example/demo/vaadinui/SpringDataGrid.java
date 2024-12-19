package com.example.demo.vaadinui;

import com.vaadin.flow.function.SerializableFunction;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.data.domain.PageRequest;
import org.vaadin.firitin.components.grid.VGrid;

import java.util.stream.Stream;

public class SpringDataGrid<T> extends VGrid<T> {

    interface LazyDataBinding<T> extends SerializableFunction<PageRequest, Stream<T>> {

    }

    public SpringDataGrid(Class<T> beanType) {
        super(beanType);
    }

    void updateBinding(LazyDataBinding<T> binding) {
        setItems(query -> {
            PageRequest springPageRequest = VaadinSpringDataHelpers.toSpringPageRequest(query);
            return binding.apply(springPageRequest);
        });
    }

}
