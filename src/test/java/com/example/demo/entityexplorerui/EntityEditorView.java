package com.example.demo.entityexplorerui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.HasHelper;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.html.Emphasis;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import jakarta.persistence.MapsId;
import jakarta.persistence.metamodel.Attribute;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.ManagedType;
import jakarta.persistence.metamodel.SingularAttribute;
import org.vaadin.firitin.appframework.MenuItem;
import org.vaadin.firitin.components.button.VButton;
import org.vaadin.firitin.components.customfield.VCustomField;
import org.vaadin.firitin.components.grid.GridSelect;
import org.vaadin.firitin.components.orderedlayout.VVerticalLayout;
import org.vaadin.firitin.components.popover.PopoverButton;
import org.vaadin.firitin.rad.AutoForm;
import org.vaadin.firitin.rad.AutoFormContext;
import org.vaadin.firitin.rad.PrettyPrinter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Route(layout = TopLayout.class)
@MenuItem(hidden = true)
public class EntityEditorView extends VVerticalLayout implements EntityManagerAwareComponent {

    private static ObjectMapper jack = new ObjectMapper();
    private final AutoFormContext ctx;
    private EntityType entityType;

    public EntityEditorView() {
        ctx = new AutoFormContext();
        // Probably not interesting for most, autoupdating this if the entity is updated
        ctx.getHiddenProperties().add("lastUpdate");

        ctx.withPropertyEditor(propertyContext -> {
            String name = propertyContext.getName();
            Attribute attr = entityType.getAttribute(name);
            if (attr.isAssociation()) {
                if (attr.getPersistentAttributeType() == Attribute.PersistentAttributeType.MANY_TO_ONE) {
                    return new GenericManyToOneEditor(attr);
                } else if (attr.getPersistentAttributeType() == Attribute.PersistentAttributeType.ONE_TO_ONE) {
                    if (true) {
                        return new OneToOneEditor();
                    }
                }
            }

            return null;
        });
        add(new Paragraph("Pick entity type from the menu"));
    }

    private static void showPropertyDetails(EntityType entityType, AutoForm<Object> form) {
        List<String> boundProperties = form.getBinder().getBoundProperties();
        for (String boundProperty : boundProperties) {
            Attribute attribute = entityType.getAttribute(boundProperty);
            Member javaMember = attribute.getJavaMember();
            if (javaMember instanceof Field field) {
                var metadata = new StringBuilder();
                metadata.append(attribute.getName());
                metadata.append(" ");
                metadata.append(field.getType().getName());
                metadata.append(" ");
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    metadata.append("@");
                    metadata.append(annotation.annotationType().getSimpleName());
                    metadata.append("(");
                    Method[] declaredMethods = annotation.annotationType().getDeclaredMethods();
                    boolean first = true;
                    for (Method m : declaredMethods) {
                        try {
                            Object pv = m.invoke(annotation);
                            Parameter[] parameters = m.getParameters();
                            Object defaultValue = m.getDefaultValue();
                            if (pv.getClass().isArray()) {
                                pv = jack.writeValueAsString(pv);
                                if (defaultValue != null) {
                                    defaultValue = jack.writeValueAsString(defaultValue);
                                }
                            }
                            if (defaultValue == null || !pv.equals(defaultValue)) {
                                if (first) {
                                    first = false;
                                } else {
                                    metadata.append(", ");
                                }
                                metadata.append(m.getName()).append("=");
                                if (pv instanceof String) {
                                    String s = (String) pv;
                                    metadata.append("\"");
                                    metadata.append(s);
                                    metadata.append("\"");
                                } else {
                                    metadata.append(pv);
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    metadata.append(")");
                    metadata.append(" ");
                }
                HasValue editor = form.getBinder().getEditor(boundProperty);

                if (editor instanceof HasHelper ht) {
                    ht.setHelperText(metadata.toString());
                }
            }
        }
    }

    public void editEntity(Object detachedEntity) {
        Object merged = getEntityManager().merge(detachedEntity);
        editEntity(merged, getEntityManagerFactory().getMetamodel().entity(merged.getClass()));
    }

    public void editEntity(Object detachedEntity, EntityType entityType) {
        removeAll();
        this.entityType = entityType;
        var em = getEntityManager();
        Object entity = em.merge(detachedEntity);
        findAncestor(TopLayout.class).setViewTitle("Editing " + entity.getClass().getSimpleName());
        AutoForm<Object> form = ctx.createForm(entity);
        form.withBeanValidation();
        form.setSaveHandler(e -> {
            // Auto update lastUpdate
            try {
                Attribute attribute = entityType.getAttribute("lastUpdate");
                if(attribute != null) {
                    Field javaMember = (Field) attribute.getJavaMember();
                    javaMember.set(entity, Instant.now());
                }
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            navigate(EntityExplorer.class)
                    .ifPresent(view -> view.setEntityType(entityType));
        });

        add(form.getFormBody());
        add(form.getActions());
        form.getBinder().addValueChangeListener(e -> {

            Object value = e.getValue();
            boolean valid = form.getBinder().isValid();
            System.out.println("Value: " + value + " valid: " + valid);

        });

        add(new VButton("Display property details...", e -> {
            showPropertyDetails(entityType, form);
            e.getSource().removeFromParent();
        }).withThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE, ButtonVariant.LUMO_SMALL));

    }

    private class GenericManyToOneEditor extends CustomField<Object> {

        private final SingularAttribute attr;
        Object value;
        Emphasis currentValue = new Emphasis() {{
            getStyle().setWhiteSpace(Style.WhiteSpace.NOWRAP);
            getStyle().setOverflow(Style.Overflow.HIDDEN);
            getStyle().set("text-overflow", "ellipsis");
            getStyle().setMaxWidth("400px");
        }};
        PopoverButton popoverButton = new PopoverButton(() -> {
            // from a grid
            // For many to one we can use a Grid to select the entity (ComboBox would be kind of better UX, but no easy way to do a good toString/filtering
            ManagedType managedType = getEntityManagerFactory().getMetamodel().managedType(getJavaType());

            Set<Attribute> attributeSet = managedType.getAttributes();
            // TODO refactor to use the JpaEntityGrid
            GridSelect gridSelect = new GridSelect<>(getJavaType());
            gridSelect.setMinWidth("70vw");

            gridSelect.setItems(query -> {
                return getEntityManager().createQuery("SELECT e FROM " + getJavaType().getSimpleName() + " e")
                        .setFirstResult(query.getOffset())
                        .setMaxResults(query.getLimit()).getResultList().stream();
            });

            gridSelect.addValueChangeListener(e -> {
                value = e.getValue();
                GenericManyToOneEditor.this.updateValue();
                setPresentationValue(value);
                gridSelect.findAncestor(Popover.class).close();
            });
            return new VVerticalLayout(new H4("Pick a new value for relation:"), gridSelect);
        });

        public GenericManyToOneEditor(Attribute attr) {
            this.attr = (SingularAttribute) attr;
            popoverButton.setTooltipText("Pick a new value...");
            add(new HorizontalLayout() {{
                setAlignItems(Alignment.BASELINE);
                setWidthFull();
                addAndExpand(currentValue);
                add(popoverButton);
            }});
            Member javaMember = this.attr.getJavaMember();
            if (javaMember instanceof Field field) {
                if (field.getAnnotation(MapsId.class) != null) {
                    setReadOnly(true);
                    GenericManyToOneEditor.this.setTooltipText("Part of id, immutable");
                }
            }
        }

        private Class<Object> getJavaType() {
            return attr.getType().getJavaType();
        }

        @Override
        protected Object generateModelValue() {
            return value;
        }

        @Override
        protected void setPresentationValue(Object o) {
            String string = "→ " + PrettyPrinter.printOneLiner(o, 400);
            currentValue.setText(string);
            currentValue.setTitle(string); // maybe clipped by browser/css
        }

        @Override
        public void setReadOnly(boolean readOnly) {
            super.setReadOnly(readOnly);
            popoverButton.setEnabled(!readOnly);
        }
    }

    public class OneToOneEditor extends VCustomField<Object> {

        private HorizontalLayout content = new HorizontalLayout();

        public OneToOneEditor() {
            super();
            add(content);
        }

        @Override
        protected OneToOneEditor generateModelValue() {
            return null;
        }

        @Override
        protected void setPresentationValue(Object o) {
            content.removeAll();
            if (o == null) {
                content.add(new Span("null"));
            } else {
                content.add(new Emphasis(PrettyPrinter.printOneLiner(o, 40)));
                content.add(new VButton(VaadinIcon.EDIT, () -> {
                    navigate(EntityEditorView.class).ifPresent(entityEditorView ->
                            entityEditorView.editEntity(o));
                }).withTooltip("Edit referenced (1-1) entity..."));
            }
        }

    }
}
