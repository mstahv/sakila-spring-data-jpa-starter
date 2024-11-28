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

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

import static java.util.Optional.ofNullable;

/**
 * An entity class for binding {@link #TABLE_NAME} table.
 * <blockquote>
 * The {@code film_category} table is used to support a many-to-many relationship between films and categories. For each
 * category applied to a film, there will be one row in the {@code film_category} table listing the category and film.
 * <p>
 * The {@code film_category} table refers to the {@link Film film} and {@link Category category} tables using foreign
 * keys.
 * </blockquote>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-film_category.html">The
 * <code>film_category</code> Table (Sakila Sample Database, Developer Zone, MySQL)</a>
 */
@IdClass(FilmCategoryId.class)
@Entity
@Table(name = FilmCategory.TABLE_NAME)
public class FilmCategory {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity. The value {@value}.
     */
    public static final String TABLE_NAME = "film_category";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_FILM} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key identifying the film.
     * </blockquote>
     * {@code SMALLINT(5) PK NN UN}
     */
    public static final String COLUMN_NAME_FILM_ID = "film_id";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_FILM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FILM = "film";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_CATEGORY} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key identifying the category.
     * </blockquote>
     * {@code TINYINT(3) PK NN UN}
     */
    public static final String COLUMN_NAME_CATEGORY_ID = "category_id";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_CATEGORY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CATEGORY = "category";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public FilmCategory() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "film=" + film
               + ",category=" + category
               + ",lastUpdate=" + lastUpdate
               + "}";
    }

    // TODO: 2019-07-10 equals/hashCode???

    // ------------------------------------------------------------------------------------------------------------ film

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_FILM} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_FILM} attribute.
     */
    public Film getFilm() {
        return film;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_FILM} attribute with specified value.
     *
     * @param film new value for {@value #ATTRIBUTE_NAME_FILM} attribute.
     */
    public void setFilm(final Film film) {
        this.film = film;
    }

    // -------------------------------------------------------------------------------------------------------- category

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_CATEGORY category} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_CATEGORY category} attribute.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_CATEGORY category} attribute with specified value.
     *
     * @param category new value for {@link #ATTRIBUTE_NAME_CATEGORY category} attribute.
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    // ----------------------------------------------------------------------------------------------------- lastUpdated

    /**
     * Returns the current value of {@link BaseEntity#ATTRIBUTE_NAME_LAST_UPDATE} attribute.
     *
     * @return the current value of {@link BaseEntity#ATTRIBUTE_NAME_LAST_UPDATE} attribute.
     */
    public Date getLastUpdate() {
        return ofNullable(lastUpdate).map(v -> new Date(v.getTime())).orElse(null);
    }

    @Deprecated
        // forTestingPurposeOnly = true
    void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_FILM_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_FILM)
    private Film film;

    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_CATEGORY_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CATEGORY)
    private Category category;

    // -----------------------------------------------------------------------------------------------------------------
    //@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = BaseEntity.COLUMN_NAME_LAST_UPDATE, nullable = false, insertable = false, updatable = false)
    @NamedAttribute(BaseEntity.ATTRIBUTE_NAME_LAST_UPDATE)
    private Date lastUpdate;
}
