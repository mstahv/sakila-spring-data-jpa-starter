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

import java.io.Serializable;
import java.util.Objects;

/**
 * An id class for {@link FilmActor}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class FilmActorId implements Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    private static final long serialVersionUID = 8539750634726943501L;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public FilmActorId() {
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
               + "actor=" + actor
               + ",film=" + film
               + "}";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilmActorId)) {
            return false;
        }
        final FilmActorId that = (FilmActorId) obj;
        return Objects.equals(getActor(), that.getActor()) && Objects.equals(getFilm(), that.getFilm());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getActor(), getFilm());
    }

    // ----------------------------------------------------------------------------------------------------------- actor
    public Integer getActor() {
        return actor;
    }

    public void setActor(final Integer actor) {
        this.actor = actor;
    }

    // ------------------------------------------------------------------------------------------------------------ film
    public Integer getFilm() {
        return film;
    }

    public void setFilm(final Integer film) {
        this.film = film;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NamedAttribute(FilmActor.ATTRIBUTE_NAME_ACTOR)
    private Integer actor;

    @NamedAttribute(FilmActor.ATTRIBUTE_NAME_FILM)
    private Integer film;
}
