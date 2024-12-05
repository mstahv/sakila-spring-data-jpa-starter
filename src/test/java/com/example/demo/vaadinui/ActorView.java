package com.example.demo.vaadinui;

import com.example.demo.sakilaentities.Actor;
import com.example.demo.sakilaentities.Customer;
import com.example.demo.springdata.ActorRepository;
import com.example.demo.springdata.CustomerRepository;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.vaadin.firitin.components.grid.VGrid;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;

@Route(layout = TopLayout.class)
public class ActorView extends VVerticalLayout {
    public ActorView(ActorRepository actorRepository) {

        add(new ActorGrid(actorRepository));
    }

    public static class ActorGrid extends VGrid<Actor> {

        public ActorGrid(ActorRepository actorRepository) {
            super(Actor.class);
            getColumns().forEach(column -> column.setAutoWidth(true));
            setItems(VaadinSpringDataHelpers.fromPagingRepository(actorRepository));
        }
    }
}
