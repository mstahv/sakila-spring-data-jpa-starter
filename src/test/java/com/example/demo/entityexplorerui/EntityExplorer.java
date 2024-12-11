package com.example.demo.entityexplorerui;

import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.EntityType;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;

@Route(layout = TopLayout.class)
@MenuItem(hidden = true)
public class EntityExplorer extends VVerticalLayout implements HasUrlParameter<String>, AfterNavigationObserver {
    private final EntityManagerFactory entityManagerFactory;
    private EntityType<?> entityType;

    public EntityExplorer(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void setEntityType(EntityType<?> entityType) {
        removeAll();
        this.entityType = entityType;
        JpaEntityGrid<Object> grid = new JpaEntityGrid<>(entityType);
        addAndExpand(grid);
        updateViewTitle();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, @OptionalParameter String s) {
        if (s != null) {
            if (entityType != null && entityType.getName().equals(s)) {
                return;
            }
            // If coming in with deep link, we need to find the entity type
            EntityType<?> entityType = entityManagerFactory.getMetamodel().getEntities().stream()
                    .filter(e -> e.getName().equals(s))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No entity with name " + s));
            setEntityType(entityType);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        updateViewTitle();
    }

    private void updateViewTitle() {
        if (isAttached() && entityType != null) {
            findAncestor(TopLayout.class).setViewTitle("Entity Explorer: " + entityType.getName());
        }
    }
}
