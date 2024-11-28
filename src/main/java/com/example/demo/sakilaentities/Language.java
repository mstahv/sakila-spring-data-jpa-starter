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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.Language.COLUMN_NAME_LANGUAGE_ID;
import static com.example.demo.sakilaentities.Language.TABLE_NAME;
import static java.util.Comparator.comparing;

/**
 * An entity class for {@value #TABLE_NAME} table.
 * <blockquote>
 * The language table is a lookup table listing the possible languages that films can have for their language and
 * original language values.
 * <p>
 * The language table is referred to by the {@link Film film} table.
 * </blockquote>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_LANGUAGE_ID, nullable = false))
@Entity
@Table(name = TABLE_NAME)
public class Language extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity. The value is {@value}.
     */
    public static final String TABLE_NAME = "language";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of this entity. The value is {@value}.
     * <blockquote>
     * A surrogate primary key used to uniquely identify each language.
     * </blockquote>
     * {@code TINYINT(3) PK NN UN AI}
     */
    public static final String COLUMN_NAME_LANGUAGE_ID = "language_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@link #ATTRIBUTE_NAME_NAME} attribute. The value is {@value}.
     * <blockquote>
     * The English name of the language.
     * </blockquote>
     * {@code CHAR(20) NN}
     */
    // TODO: 2019-07-17 not VARCHAR???
    public static final String COLUMN_NAME_NAME = "name";

    /**
     * The entity attribute name for {@link #COLUMN_NAME_NAME} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_NAME = "name";

    /**
     * The minimum length for {@link #ATTRIBUTE_NAME_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_NAME = 0; // TODO: 2019-07-17 empty???

    /**
     * The maximum length for {@link #ATTRIBUTE_NAME_NAME} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_NAME = 20;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_FILMS = "films";

    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public static final int SIZE_MIN_FILMS = 0;

    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public static final int SIZE_MAX_FILMS = Integer.MAX_VALUE; // TODO: 2019-07-17 what???

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_FILMS_RECORDED = "filmsRecorded";

    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public static final int SIZE_MIN_FILMS_RECORDED = 0;

    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public static final int SIZE_MAX_FILMS_RECORDED = Integer.MAX_VALUE; // TODO: 2019-07-17 what???

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator for comparing with {@link #ATTRIBUTE_NAME_NAME} attribute in natural direction.
     */
    public static final Comparator<Language> COMPARING_NAME = comparing(Language::getName);

    /**
     * Returns a comparator for comparing with {@link #ATTRIBUTE_NAME_NAME} attribute in either natural or reverse
     * direction.
     *
     * @param natural a flag for comparison direction; {@code true} for natural direction, {@code false} for reverse
     *                direction.
     * @return a comparator.
     */
    public static Comparator<Language> comparingName(final boolean natural) {
        return natural ? COMPARING_NAME : COMPARING_NAME.reversed();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Language() {
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

    // TODO: 2019-07-17 equals/hashCode???

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
    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public Set<Film> getFilms() {
        if (films == null) {
            films = new HashSet<>();
        }
        return films;
    }

    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public boolean addFilm(final Film film) {
        if (film == null) {
            throw new NullPointerException("film is null");
        }
        final boolean filmAdded = getFilms().add(film);
        if (film.getLanguage() != this) {
            film.setLanguage(this);
        }
        return filmAdded;
    }

    // --------------------------------------------------------------------------------------------------- filmsRecorded
    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public Set<Film> getFilmsRecorded() {
        if (filmsRecorded == null) {
            filmsRecorded = new HashSet<>();
        }
        return filmsRecorded;
    }

    // TODO: 2019-07-17 remove!!!
    @Deprecated // forRemoval = true
    public boolean addFilmRecorded(final Film filmRecorded) {
        if (filmRecorded == null) {
            throw new NullPointerException("filmRecorded is null");
        }
        final boolean filmRecordedAdded = getFilmsRecorded().add(filmRecorded);
        if (filmRecorded.getOriginalLanguage() != this) {
            filmRecorded.setOriginalLanguage(this);
        }
        return filmRecordedAdded;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(min = SIZE_MIN_NAME, max = SIZE_MAX_NAME)
    @NotBlank
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_NAME, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_NAME)
    private String name;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-17 remove!!!
    @Deprecated
    @OneToMany(
            cascade = {
                    //CascadeType.ALL,
                    //CascadeType.DETACH,
                    //CascadeType.MERGE,
                    //CascadeType.PERSIST,
                    //CascadeType.REFRESH,
                    //CascadeType.REMOVE
            },
            fetch = FetchType.LAZY, // default
            mappedBy = Film.ATTRIBUTE_NAME_LANGUAGE,
            orphanRemoval = false
    )
    @NamedAttribute(ATTRIBUTE_NAME_FILMS)
    private @Size(min = SIZE_MIN_FILMS, max = SIZE_MAX_FILMS) Set<@NotNull Film> films;

    // TODO: 2019-07-17 remove!!!
    @Deprecated
    @OneToMany(
            cascade = {
                    //CascadeType.ALL,
                    //CascadeType.DETACH,
                    //CascadeType.MERGE,
                    //CascadeType.PERSIST,
                    //CascadeType.REFRESH,
                    //CascadeType.REMOVE
            },
            fetch = FetchType.LAZY, // default
            mappedBy = Film.ATTRIBUTE_NAME_ORIGINAL_LANGUAGE,
            orphanRemoval = false
    )
    @NamedAttribute(ATTRIBUTE_NAME_FILMS_RECORDED)
    private @Size(min = SIZE_MIN_FILMS_RECORDED, max = SIZE_MAX_FILMS_RECORDED) Set<@NotNull Film> filmsRecorded;
}
