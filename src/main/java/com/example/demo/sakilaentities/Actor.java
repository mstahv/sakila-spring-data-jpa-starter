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

//    // -----------------------------------------------------------------------------------------------------------------
//
//    /**
//     * Returns a string representation of the object.
//     *
//     * @return a string representation of the object.
//     */
//    @Override
//    public String toString() {
//        return super.toString() + "{"
//               + "firstName=" + firstName
//               + ",lastName=" + lastName
//               + "}";
//    }

//    // ------------------------------------------------------------------------------------------------------- firstName
//
//    /**
//     * {@inheritDoc}
//     *
//     * @return {@inheritDoc}
//     */
//    @Override
//    public String getFirstName() {
//        return firstName;
//    }
//
//    /**
//     * {@inheritDoc}
//     *
//     * @param firstName new value for {@link #ATTRIBUTE_NAME_FIRST_NAME} attribute.
//     */
//    @Override
//    public void setFirstName(final String firstName) {
//        this.firstName = firstName;
//    }
//
//    // -------------------------------------------------------------------------------------------------------- lastName
//
//    /**
//     * {@inheritDoc}
//     *
//     * @return {@inheritDoc}
//     */
//    @Override
//    public String getLastName() {
//        return lastName;
//    }
//
//    /**
//     * {@inheritDoc}
//     *
//     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
//     */
//    @Override
//    public void setLastName(final String lastName) {
//        this.lastName = lastName;
//    }

    // ----------------------------------------------------------------------------------------------------------- films
    // TODO: 2019-07-14 remove!!!
    @Deprecated // forRemoval = true
    public Set<Film> getFilms() {
        if (films == null) {
            films = new HashSet<>();
        }
        return films;
    }

    // TODO: 2019-07-12 remove!!!
    @Deprecated // forRemoval = true
    public boolean addFilm(final Film film) {
        if (film == null) {
            throw new NullPointerException("film is null");
        }
        final boolean filmAdded = getFilms().add(film); // TODO: 2019-07-23 equals/hashCode???
        if (!film.getActors().contains(this)) {
            final boolean addedToFilm = film.addActor(this);
        }
        return filmAdded;
    }

//    // -----------------------------------------------------------------------------------------------------------------
//    @Size(min = SIZE_MIN_FIRST_NAME, max = SIZE_MAX_FIRST_NAME)
//    @NotNull
//    @Basic(optional = false)
//    @Column(name = COLUMN_NAME_FIRST_NAME, nullable = false, length = SIZE_MAX_FIRST_NAME)
//    @NamedAttribute(ATTRIBUTE_NAME_FIRST_NAME)
//    private String firstName;
//
//    @Size(min = SIZE_MIN_LAST_NAME, max = SIZE_MAX_LAST_NAME)
//    @NotNull
//    @Basic(optional = false)
//    @Column(name = COLUMN_NAME_LAST_NAME, nullable = false, length = SIZE_MAX_LAST_NAME)
//    @NamedAttribute(ATTRIBUTE_NAME_LAST_NAME)
//    private String lastName;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-14 remove!!!
    @Deprecated
    @ManyToMany(
            cascade = {
//                    CascadeType.ALL,
//                    CascadeType.DETACH,
//                    CascadeType.MERGE,
//                    CascadeType.PERSIST,
//                    CascadeType.REFRESH,
//                    CascadeType.REMOVE
            },
            fetch = FetchType.LAZY, // default
            targetEntity = Film.class
    )
    @JoinTable(name = FilmActor.TABLE_NAME,
               joinColumns = {
                       @JoinColumn(name = FilmActor.COLUMN_NAME_ACTOR_ID, referencedColumnName = COLUMN_NAME_ACTOR_ID)
               },
               inverseJoinColumns = {
                       @JoinColumn(name = FilmActor.COLUMN_NAME_FILM_ID,
                                   referencedColumnName = Film.COLUMN_NAME_FILM_ID)
               }
    )
    @NamedAttribute(ATTRIBUTE_NAME_FILMS)
    private Set<Film> films;
}
