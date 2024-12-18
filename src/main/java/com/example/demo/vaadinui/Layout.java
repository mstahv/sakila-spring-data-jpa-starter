package com.example.demo.vaadinui;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.RouteConfiguration;
import in.virit.entityexplorer.EntityEditorView;
import in.virit.entityexplorer.EntityExplorer;
import in.virit.entityexplorer.EntityManagerAwareComponent;
import org.vaadin.firitin.appframework.MainLayout;

public class Layout extends MainLayout implements EntityManagerAwareComponent {

    public Layout() {
    }

    @Override
    protected String getDrawerHeader() {
        return "}> Sakila";
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);

        // add section for entity explorer views directly to main nav
        addEntityExplorerViews();

    }

    private void addEntityExplorerViews() {

        var adminNav = new SideNav();
        adminNav.setLabel("Admin (Entity Explorer)");
        adminNav.setCollapsible(true);

        getEntityManagerFactory().getMetamodel().getEntities().forEach(entityType -> {
            adminNav.addItem(new SideNavItem(entityType.getName(), "entityexplorer/" + entityType.getName()){{
                getStyle().setMarginTop("-1em");
                getStyle().setMarginBottom("-0.8em");
            }});
        });

        addToDrawer(adminNav);
    }
}
