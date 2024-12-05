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
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;

import static com.example.demo.sakilaentities.Actor.TABLE_NAME;
import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;


/**
 * An entity class for binding {@value TABLE_NAME} table.
 * <blockquote>
 * The {@code actor} table lists information for all actors.
 * <p>
 * The {@code actor} table is joined to the {@link Film film} table by means of the {@link FilmActor film_actor} table.
 * </blockquote>
 *
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-actor.html">The actor table (Sakila Sample
 * Database)</a>
 */
@Entity
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = Actor.COLUMN_NAME_ACTOR_ID, nullable = false))
@Table(name = TABLE_NAME)
public class Actor extends FullNamedBaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of {@link Actor} entity. The value is {@value}.
     */
    public static final String TABLE_NAME = "actor";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of this entity class. The value is {@value}.
     * <blockquote>
     * A surrogate primary key used to uniquely identify each actor in the table.
     * </blockquote>
     * {@code SMALLINT(5) PK NN UN AI}
     */
    public static final String COLUMN_NAME_ACTOR_ID = "actor_id";

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-12 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_FILMS = "films";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Actor() {
        super();
    }


}
