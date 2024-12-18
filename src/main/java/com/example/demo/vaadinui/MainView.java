package com.example.demo.vaadinui;

import com.vaadin.flow.router.Route;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;
import org.vaadin.firitin.components.textfield.VTextField;

@Route(layout = Layout.class)
public class MainView extends VVerticalLayout {
    public MainView(ActorInfoGrid grid) {
        add("It works!? This is a dummy Vaadin view example that only lists ActorInfo view from the database in a Grid.");
        add(new FilterTextField(grid));
        addAndExpand(grid);
    }

    class FilterTextField extends VTextField {
        public FilterTextField(ActorInfoGrid grid) {
            super();
            setClearButtonVisible(true);
            setPlaceholder("Filter by first name");
            addValueChangeListener(e -> grid.filterByFirstname(e.getValue()));
        }
    }
}
