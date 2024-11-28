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

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_LAST_UPDATE;
import static com.example.demo.sakilaentities.BaseEntity.COLUMN_NAME_LAST_UPDATE;

/**
 * An entity class for {@value #TABLE_NAME} table.
 */
@IdClass(FilmActorId.class)
@Entity
@Table(name = FilmActor.TABLE_NAME)
public class FilmActor {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity class. The value is {@value}.
     */
    public static final String TABLE_NAME = "film_actor";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The table column name for {@value #ATTRIBUTE_NAME_ACTOR} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key identifying the actor.
     * </blockquote>
     * {@code SMALLINT(5) PK NN UN}
     */
    public static final String COLUMN_NAME_ACTOR_ID = "actor_id";

    public static final String ATTRIBUTE_NAME_ACTOR = "actor";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@link #ATTRIBUTE_NAME_FILM} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key identifying the film.
     * </blockquote>
     * {@code SMALLINT(5) PK NN UN}
     */
    public static final String COLUMN_NAME_FILM_ID = "film_id";

    public static final String ATTRIBUTE_NAME_FILM = "film";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public FilmActor() {
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
        return super.toString() + "{" +
               "actor=" + actor
               + ",film=" + film
               + ",lastUpdate=" + lastUpdate
               + '}';
    }

    // TODO: 2019-07-10 equals/hashCode???

    // ----------------------------------------------------------------------------------------------------------- actor
    public Actor getActor() {
        return actor;
    }

    public void setActor(final Actor actor) {
        this.actor = actor;
    }

    // ------------------------------------------------------------------------------------------------------------ film
    public Film getFilm() {
        return film;
    }

    public void setFilm(final Film film) {
        this.film = film;
    }

    // ------------------------------------------------------------------------------------------------------ lastUpdate

    /**
     * Returns the current value of {@code lastUpdate} attribute.
     *
     * @return the current value of {@code lastUpdate} attribute.
     */
    public Date getLastUpdate() {
        // TODO: 2019-07-11 copy!!!
        return lastUpdate;
    }

    void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_ACTOR_ID, referencedColumnName = Actor.COLUMN_NAME_ACTOR_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ACTOR)
    private Actor actor;

    @NotNull
    @Id
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_FILM_ID, referencedColumnName = Film.COLUMN_NAME_FILM_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_FILM)
    private Film film;

    //@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = COLUMN_NAME_LAST_UPDATE, nullable = false, insertable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_LAST_UPDATE)
    private Date lastUpdate;
}
