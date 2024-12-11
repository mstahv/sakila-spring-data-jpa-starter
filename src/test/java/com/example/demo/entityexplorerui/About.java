package com.example.demo.entityexplorerui;

import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.RichText;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;

@Route(value = "", layout = TopLayout.class)
public class About extends VVerticalLayout {
    public About(EntitySelect entitySelect) {
        add(new RichText().withMarkDown("""
        # Entity Explorer
        
        This is a tiny generic web UI for JPA backend showing all entities and allowing you to browse and
        modify them during development/testing. Note that some entities are read-only (mapping to views etc)
        so you can't modify them.
        
        DISCLAIMER: This is not a full-fledged admin UI, but a simple tool to help you during development.
        Don't use it as a starting point for your production Vaadin UIs, as this uses patterns that
        are "non-optimal" especially related to how entity manager and transactions are handled. In most
        cases it is better to implement a service layer that your Vaadin UI accesses instead of fiddling
        directly with EntityManager.
        
        """));

        add(entitySelect);
    }

}
