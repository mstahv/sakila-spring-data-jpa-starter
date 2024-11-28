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
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.Inventory.COLUMN_NAME_INVENTORY_ID;
import static com.example.demo.sakilaentities.Inventory.TABLE_NAME;

/**
 * An entity class for {@value #TABLE_NAME} table.
 * <blockquote>
 * The inventory table contains one row for each copy of a given film in a given store.
 * <p>
 * The {@code inventory} table refers to the {@link Film film} and {@link Store store} tables using foreign keys and is
 * referred to by the {@link Rental rental} table.
 * </blockquote>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-inventory.html">The inventory Table (Sakila
 * Sample Database, Developer Zone, MySQL)</a>
 */
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_INVENTORY_ID, nullable = false))
@Entity
@Table(name = TABLE_NAME)
public class Inventory extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity class. The value is {@value}.
     */
    public static final String TABLE_NAME = "inventory";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of the table. The value is {@value}.
     * <blockquote>
     * A surrogate primary key used to uniquely identify each item in inventory.
     * </blockquote>
     * {@code MEDIUMINT(8) PK NN UN AI}
     */
    public static final String COLUMN_NAME_INVENTORY_ID = "inventory_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_FILM} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key pointing to the film this item represents.
     * </blockquote>
     * {@code SMALLINT(5) NN UN}
     */
    public static final String COLUMN_NAME_FILM_ID = "film_id";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_FILM_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_FILM = "film";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_STORE} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key pointing to the store stocking this item.
     * </blockquote>
     * {@code TINYINT(3) NN UN}
     */
    public static final String COLUMN_NAME_STORE_ID = "store_id";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_STORE_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_STORE = "store";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Inventory() {
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
               + "film=" + film
               + ",store=" + store
               + "}";
    }

    // TODO: 2019-07-22 equals/hashCode???

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

    // ----------------------------------------------------------------------------------------------------------- store

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public Store getStore() {
        return store;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_STORE} attribute with specified value.
     *
     * @param store new value for {@value #ATTRIBUTE_NAME_STORE} attribute.
     */
    public void setStore(final Store store) {
        this.store = store;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_FILM_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_FILM)
    private Film film;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_STORE)
    private Store store;
}
