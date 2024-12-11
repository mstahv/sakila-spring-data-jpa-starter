package com.example.demo.entityexplorerui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.spring.annotation.SpringComponent;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.EntityType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@SpringComponent
@org.springframework.context.annotation.Scope("prototype")
class EntitySelect extends Select<EntityType<?>> {
    public EntitySelect(EntityManagerFactory entityManagerFactory) {
        setLabel("Select an entity");
        setItemLabelGenerator(EntityType::getName);
        addAttachListener(a -> {
            List<EntityType<?>> entities = new ArrayList<>(entityManagerFactory.getMetamodel().getEntities());
            Collections.sort(entities, Comparator.comparing(EntityType::getName));
            setItems(entities);
            addValueChangeListener(event -> {
                UI.getCurrent().navigate(EntityExplorer.class, event.getValue().getName());
            });
        });
    }
}
