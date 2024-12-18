package com.example.demo.vaadinui;

import com.example.demo.entities.views.ActorInfo;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.vaadin.firitin.components.grid.VGrid;

@SpringComponent
@Scope("prototype")
public class ActorInfoGrid extends VGrid<ActorInfo> {
    private final ActorInfoService actorInfoService;

    public ActorInfoGrid(ActorInfoService actorInfoService) {
        super(ActorInfo.class);
        this.actorInfoService = actorInfoService;
        getColumnByKey("id").setAutoWidth(true).setFlexGrow(0);
        getColumnByKey("filmInfo").setFlexGrow(10);
        getColumnByKey("filmInfo").getStyle().setWhiteSpace(Style.WhiteSpace.NORMAL);
        list();
    }

    public void list() {
        filterByFirstname("");
    }

    public void filterByFirstname(String value) {
        setItems(query -> {
            PageRequest springPageRequest = VaadinSpringDataHelpers.toSpringPageRequest(query);
            return actorInfoService.findByFirstName(value, springPageRequest).toList().stream();
        });
    }
}
