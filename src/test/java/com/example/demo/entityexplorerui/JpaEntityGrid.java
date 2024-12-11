package com.example.demo.entityexplorerui;

import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Emphasis;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.dom.Style;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import org.vaadin.firitin.components.button.VButton;
import org.vaadin.firitin.components.grid.VGrid;
import org.vaadin.firitin.components.orderedlayout.VHorizontalLayout;
import org.vaadin.firitin.components.popover.PopoverButton;
import org.vaadin.firitin.rad.PrettyPrinter;

public class JpaEntityGrid<T> extends VGrid<T> implements EntityManagerAwareComponent {

    private EntityType entityType;

    public JpaEntityGrid(EntityType<?> entityType) {
        super((Class<T>) entityType.getJavaType(), false);
        this.entityType = entityType;
        addThemeVariants(GridVariant.LUMO_ROW_STRIPES);

        addComponentColumn(entity -> new VHorizontalLayout(new QuickEditButton(entity), new PrettyPrintButton(entity))
                .withSpacing(false))
                .setFlexGrow(0)
                .setAutoWidth(true);

        for (BeanPropertyDefinition bpf : getBeanPropertyDefinitions()) {
            Attribute<?, ?> attribute = entityType.getAttribute(bpf.getName());
            Column column;
            if (!attribute.isAssociation()) {
                // Let Vaadin figure out the best renderer
                column = addColumn(bpf.getName());
                if (bpf.getName().equals("id") || bpf.getName().equals("lastUpdate")) {
                    column.setVisible(false);
                }
                column.getStyle().setMaxWidth("350px");
            } else {
                column = addComponentColumn(entity -> {
                    Object associationValue = bpf.getGetter().getValue(entity);
                    return new AssociationColumn(associationValue);
                }).setHeader(attribute.getName());
            }
            column.setResizable(true);
            column.setAutoWidth(true);
            column.setSortable(false);
        }
        withColumnSelector();

        setItems(query -> {
            // TODO consider supporting sorting & filtering
            return getEntityManager().createQuery("select e from " + entityType.getName() + " e")
                    .setFirstResult(query.getOffset())
                    .setMaxResults(query.getLimit())
                    .getResultList().stream();
        });

    }

    private static class PrettyPrintButton extends PopoverButton {
        public PrettyPrintButton(Object entity) {
            super(() -> PrettyPrinter.toVaadin(entity));
            setIcon(VaadinIcon.EYE.create());
            addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            setTooltipText("Browse entity details");
        }
    }

    private static class AssociationColumn extends VHorizontalLayout {
        public AssociationColumn(Object value) {
            add(
                    new Emphasis("→ " + PrettyPrinter.printOneLiner(value, 100)) {{
                        setMaxWidth("150px");
                        getStyle().setOverflow(Style.Overflow.HIDDEN);
                        getStyle().set("text-overflow", "ellipsis");
                    }},
                    new PrettyPrintButton(value)
            );
            setSpacing(false);
        }
    }

    private class QuickEditButton extends VButton {
        public QuickEditButton(Object proxy) {
            setIcon(VaadinIcon.EDIT.create());
            addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            addClickListener(() -> {
                navigate(EntityEditorView.class).ifPresent(view -> {
                    view.editEntity(proxy, entityType);
                });
            });
            setTooltipText("Edit entity");
        }
    }

}
