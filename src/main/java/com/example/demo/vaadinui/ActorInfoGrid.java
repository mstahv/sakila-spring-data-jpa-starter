package com.example.demo.vaadinui;

import com.example.demo.entities.views.ActorInfo;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.context.annotation.Scope;

@SpringComponent
@Scope("prototype")
public class ActorInfoGrid extends SpringDataGrid<ActorInfo> {
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

    public void filterByFirstname(String filter) {
        updateBinding(pageRequest -> actorInfoService.findByFirstName(filter, pageRequest));
    }
}
