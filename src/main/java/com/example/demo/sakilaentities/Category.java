package com.example.demo.sakilaentities;

/*-
 * #%L
 * sakila-entities
 * %%
 * Copyright (C) 2019 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.Category.COLUMN_NAME_CATEGORY_ID;
import static com.example.demo.sakilaentities.Category.TABLE_NAME;
import static java.util.Comparator.comparing;

/**
 * An entity for {@value #TABLE_NAME} table.
 * <blockquote>
 * The {@code category} table lists the categories that can be assigned to a film.
 * <p>
 * The category table is joined to the <code>{@link Film film}</code> table by means of the <code>{@link FilmCategory
 * film_category}</code> table.
 * </blockquote>
 */
@Entity
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_CATEGORY_ID, nullable = false))
@Table(name = TABLE_NAME)
public class Category extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity class. The value is {@value}.
     */
    public static final String TABLE_NAME = "category";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of {@value #TABLE_NAME} table. The value is {@value}.
     * <blockquote>
     * A surrogate primary key used to uniquely identify each category in the table.
     * </blockquote>
     * {@code TINYINT(3) PK NN UN AI}
     */
    public static final String COLUMN_NAME_CATEGORY_ID = "category_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_NAME} attribute. The value is {@value}.
     * <blockquote>
     * The name of the category.
     * </blockquote>
     * {@code VARCHAR(25) NN}
     */
    public static final String COLUMN_NAME_NAME = "name";

    /**
     * The length of the {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_NAME = 25;

    /**
     * The entity attribute name for {@value #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    public static final int SIZE_MIN_NAME = 0; // TODO: 2019-07-17 empty???

    /**
     * The maximum length of {@value #ATTRIBUTE_NAME_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_NAME = COLUMN_LENGTH_NAME; // TODO: 2019-07-17 column vs attribute length ???

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The entity attribute name for those films categorized by this category. The value is {@value}.
     */
    // TODO: 2019-07-11 remove!!!
    @Deprecated
    public static final String ATTRIBUTE_NAME_FILMS = "films";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The comparator for comparing categories with {@value #ATTRIBUTE_NAME_NAME} attributes.
     */
    public static final Comparator<Category> COMPARING_NAME = comparing(Category::getName);

    public static Comparator<Category> comparingName(final boolean natural) {
        return natural ? COMPARING_NAME : COMPARING_NAME.reversed();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Category() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return super.toString() + "{"
               + "name=" + name
               + "}";
    }

    // TODO: 2019-07-11 equals/hashCode???

    // ------------------------------------------------------------------------------------------------------------ name

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public String getName() {
        return name;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_NAME} attribute with specified value.
     *
     * @param name new value for {@value #ATTRIBUTE_NAME_NAME} attribute.
     */
    public void setName(final String name) {
        this.name = name;
    }

    // ----------------------------------------------------------------------------------------------------------- films

    /**
     * Returns all films categorized to this category.
     *
     * @return a set of films categories to this category.
     * @deprecated Going to be removed!!!
     */
    // TODO: 2019-07-11 remove!!!
    @Deprecated // forRemoval = true
    public Set<Film> getFilms() {
        if (films == null) {
            films = new HashSet<>();
        }
        return films;
    }

    /**
     * Categorize specified film to this category.
     *
     * @param film the film to be categorized this this category.
     * @return {@code true} if specified film is not already categorized to this category; {@code false} otherwise.
     */
    // TODO: 2019-07-11 remove!!!
    @Deprecated // forRemoval = true
    public boolean addFilm(final Film film) {
        if (film == null) {
            throw new NullPointerException("film is null");
        }
        final boolean filmAdded = getFilms().add(film); // TODO: 2019-07-17 equals/hashCode???
        if (!film.getCategories().contains(this)) {
            final boolean addedToFilm = film.addCategory(this);
        }
        return filmAdded;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(min = SIZE_MIN_NAME, max = SIZE_MAX_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, length = COLUMN_LENGTH_NAME, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_NAME)
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-11 remove!!!
    @Deprecated // forRemoval = true
    @ManyToMany(
            cascade = {
                    //CascadeType.ALL,
                    //CascadeType.DETACH,
                    //CascadeType.MERGE,
                    //CascadeType.PERSIST,
                    //CascadeType.REFRESH,
                    //CascadeType.REMOVE
            },
            fetch = FetchType.LAZY, // default
            mappedBy = Film.ATTRIBUTE_NAME_CATEGORIES,
            targetEntity = Film.class // default; void.class
            )
    @NamedAttribute(ATTRIBUTE_NAME_FILMS)
    private Set<Film> films; // TODO: 2019-07-23 equals/hashCode???
}
