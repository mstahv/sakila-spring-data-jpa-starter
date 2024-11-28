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

import com.example.demo.sakilaentities.converter.CustomValuedEnum;
import com.example.demo.sakilaentities.converter.CustomValuedEnumAttributeConverter;
import com.example.demo.sakilaentities.converter.CustomValuedEnumSetJoinedStringAttributeConverter;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.Film.COLUMN_NAME_FILM_ID;
import static com.example.demo.sakilaentities.Film.TABLE_NAME;
import static java.lang.Math.toIntExact;
import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-film.html">The film Table (Sakila Sample
 * Database)</a>
 */
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_FILM_ID, nullable = false))
@Entity
@Table(name = TABLE_NAME)
public class Film extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity. The value is {@value}.
     */
    public static final String TABLE_NAME = "film";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of this entity. The value is {@value}.
     * <blockquote>
     * A surrogate primary key used to uniquely identify each film in the table.
     * </blockquote>
     * {@code SMALLINT(5) PK NN UN AI}
     */
    public static final String COLUMN_NAME_FILM_ID = "film_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_TITLE} attribute. The value is {@value}.
     * <blockquote>
     * The title of the film.
     * </blockquote>
     * {@code title VARCHAR(255) NN}
     **/
    public static final String COLUMN_NAME_TITLE = "title";

    public static final String ATTRIBUTE_NAME_TITLE = "title";

    public static final int SIZE_MAX_TITLE = 255;

    /**
     * A comparator for comparing {@link #ATTRIBUTE_NAME_TITLE title} attribute.
     */
    public static final Comparator<Film> COMPARING_TITLE = comparing(Film::getTitle);

    /**
     * Returns a comparator for comparing {@link #ATTRIBUTE_NAME_TITLE title} attribute in either natural or reversed
     * indicated by specified flag.
     *
     * @param natural the flag for ordering; {@code true} for natual ordering; {@code false} for reversed ordering.
     * @return a comparator for comparing {@link #ATTRIBUTE_NAME_TITLE title} attribute.
     */
    public static Comparator<Film> comparingTitle(final boolean natural) {
        return natural ? COMPARING_TITLE : COMPARING_TITLE.reversed();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database table column name for {@value #ATTRIBUTE_NAME_DESCRIPTION} attribute. The value is {@value}.
     * <blockquote>
     * A short description or plot summary of the film
     * </blockquote>
     * {@code VARCHAR(255) NULL}
     **/
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    public static final int SIZE_MAX_DESCRIPTION = 65535; // TEXT

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_RELEASE_YEAR} attribute. The value is {@value}.
     * <blockquote>
     * The year in which the movie was released.
     * </blockquote>
     * {@code YEAR NULL}
     *
     * @see <a href="https://dev.mysql.com/doc/refman/8.0/en/year.html">The YEAR Type (MySQL Reference Manual)</a>
     **/
    public static final String COLUMN_NAME_RELEASE_YEAR = "release_year";

    public static final String ATTRIBUTE_NAME_RELEASE_YEAR = "releaseYear";

    public static final int MIN_RELEASE_YEAR = 1901;

    public static final int MAX_RELEASE_YEAR = 2155;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_LANGUAGE} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key pointing at the {@link Language language} table; identifies the language of the film.
     * </blockquote>
     * {@code TINYINT(3) NN UN}
     */
    public static final String COLUMN_NAME_LANGUAGE_ID = "language_id";

    public static final String ATTRIBUTE_NAME_LANGUAGE = "language";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_ORIGINAL_LANGUAGE} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key pointing at the {@link Language language} table; identifies the original language of the film. Used
     * when a film has been dubbed into a new language.
     * </blockquote>
     * {@code TINYINT(3) UN NULL}
     **/
    public static final String COLUMN_NAME_ORIGINAL_LANGUAGE_ID = "original_language_id";

    public static final String ATTRIBUTE_NAME_ORIGINAL_LANGUAGE = "originalLanguage";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_RENTAL_DURATION} attribute. The value is {@value}.
     * <blockquote>
     * The length of the rental period, in days.
     * </blockquote>
     * {@code TINYINT(3) NN UN '3'}
     **/
    public static final String COLUMN_NAME_RENTAL_DURATION = "rental_duration";

    public static final String ATTRIBUTE_NAME_RENTAL_DURATION = "rentalDuration";

    public static final int MIN_RENTAL_DURATION = 0; // 1?

    public static final int MAX_RENTAL_DURATION = 255;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_RENTAL_RATE} attribute. The value is {@value}.
     * <blockquote>
     * The cost to rent the film for the period specified in the rental_duration column.
     * </blockquote>
     * {@code DECIMAL(4,2) NN '4.99'}
     **/
    public static final String COLUMN_NAME_RENTAL_RATE = "rental_rate";

    public static final String ATTRIBUTE_NAME_RENTAL_RATE = "rentalRate";

    public static final int DIGITS_INTEGER_RENTAL_RATE = 4;

    public static final int DIGITS_FRACTION_RENTAL_RATE = 2;

    public static final String DECIMAL_MIN_RENTAL_RATE = "0000.00"; // free?

    public static final String DECIMAL_MAX_RENTAL_RATE = "9999.99"; // really?

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_LENGTH} attribute. The value is {@value}.
     * <blockquote>
     * The duration of the film, in minutes.
     * </blockquote>
     * {@code SMALLINT(5) UN NULL}
     */
    public static final String COLUMN_NAME_LENGTH = "length";

    public static final String ATTRIBUTE_NAME_LENGTH = "length";

    public static final int MIN_LENGTH = 0; // less than a minute?

    public static final int MAX_LENGTH = 65535; // 1092.25 hours?

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_REPLACEMENT_COST} attribute. The value is {@value}.
     * <blockquote>
     * The amount charged to the customer if the film is not returned or is returned in a damaged state.
     * </blockquote>
     * {@code DECIMAL(5,2) NN '19.99'}
     **/
    public static final String COLUMN_NAME_REPLACEMENT_COST = "replacement_cost";

    public static final String ATTRIBUTE_NAME_REPLACEMENT_COST = "replacementCost";

    public static final int DIGITS_INTEGER_REPLACEMENT_COST = 5;

    public static final int DIGITS_FRACTION_REPLACEMENT_COST = 2;

    public static final String DECIMAL_MIN_REPLACEMENT_COST = "00000.00"; // no charge at all?

    public static final String DECIMAL_MAX_REPLACEMENT_COST = "99999.99"; // seriously?

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name name for {@value #ATTRIBUTE_NAME_RATING} attribute. The value is {@value}.
     * <blockquote>
     * The rating assigned to the film. Can be one of: G, PG, PG-13, R, or NC-17.
     * </blockquote>
     * {@code ENUM('G', 'PG', 'PG-13', 'R', 'NC-17') 'G'}
     */
    public static final String COLUMN_NAME_RATING = "rating";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_RATING} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_RATING = "rating";

    /**
     * Rating for {@value}(General Audiences). <i>All ages admitted</i>. Nothing that would offend parents for viewing
     * by children.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system#MPAA_film_ratings">MPAA
     * film ratings (Wikipedia)</a>
     */
    public static final String RATING_G = "G";

    /**
     * Rating for {@value}(Parental Guidance Suggested). <i>Some material may not be suitable for children</i>. Parents
     * urged to give "parental guidance". May contain some material parents might not like for their young children.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system#MPAA_film_ratings">MPAA
     * film ratings (Wikipedia)</a>
     */
    public static final String RATING_PG = "PG";

    /**
     * Rating for {@value}(Parents Strongly Cautioned). <i>Some material may be inappropriate for children under 13</i>.
     * Parents are urged to be cautious. Some material may be inappropriate for pre-teenagers.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system#MPAA_film_ratings">MPAA
     * film ratings (Wikipedia)</a>
     */
    public static final String RATING_PG_13 = "PG-13";

    /**
     * Rating for {@value}(Restricted). <i>Under 17 requires accompanying parent or adult guardian</i>. Contains some
     * adult material. Parents are urged to learn more about the film before taking their young children with them.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system#MPAA_film_ratings">MPAA
     * film ratings (Wikipedia)</a>
     */
    public static final String RATING_R = "R";

    /**
     * Rating for {@value}(Adults Only). <i>No One 17 and Under Admitted</i>. Clearly adult. Children are not admitted.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system#MPAA_film_ratings">MPAA
     * film ratings (Wikipedia)</a>
     */
    public static final String RATING_NC_17 = "NC-17";

    /**
     * Constants for {@link #ATTRIBUTE_NAME_RATING rating} attribute.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Motion_Picture_Association_of_America_film_rating_system#MPAA_film_ratings">MPAA
     * film ratings (Wikipedia)</a>
     */
    public enum Rating implements CustomValuedEnum<Rating, String> {

        /**
         * Constant for {@link #RATING_G}.
         */
        GENERAL_AUDIENCES("G"),

        /**
         * Constants for {@link #RATING_PG}.
         */
        PARENTAL_GUIDANCE_SUGGESTED("PG"),

        /**
         * Constants for {@link #RATING_PG_13}.
         */
        PARENTS_STRONGLY_CAUTIONED("PG-13"),

        /**
         * Constants for {@link #RATING_R}.
         */
        RESTRICTED("R"),

        /**
         * Constants for {@link #RATING_NC_17}.
         */
        ADULTS_ONLY("NC-17");

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Returns the value whose {@code databaseColumn} attribute equals to specified value.
         *
         * @param databaseColumn the value for {@code databaseColumn} to match.
         * @return The matched value.
         * @see CustomValuedEnum#valueOfDatabaseColumn(Class, Object)
         */
        public static Rating valueOfDatabaseColumn(final String databaseColumn) {
            return CustomValuedEnum.valueOfDatabaseColumn(Rating.class, databaseColumn);
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Creates a new instance.
         *
         * @param databaseColumn a value for {@code databaseColumn} attribute.
         */
        Rating(final String databaseColumn) {
            this.databaseColumn = requireNonNull(databaseColumn);
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         */
        @Override
        public String getDatabaseColumn() {
            return databaseColumn;
        }

        // -------------------------------------------------------------------------------------------------------------
        private final String databaseColumn;
    }

    // -------------------------------------------------------------------------------------------------------------

    /**
     * An attribute converter for converting constants to database values and vice versa.
     */
    @Converter
    public static class RatingAttributeConverter extends CustomValuedEnumAttributeConverter<Rating, String> {

        /**
         * Creates a new instance.
         */
        public RatingAttributeConverter() {
            super(Rating.class);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The column name for {@value #ATTRIBUTE_NAME_SPECIAL_FEATURES} attribute. The value is {@value}.
     * <blockquote>
     * Lists which common special features are included on the DVD.
     * <p>
     * Can be zero or more of: Trailers, Commentaries, Deleted Scenes, Behind the Scenes.
     * </blockquote>
     * {@code SET('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes') NULL}
     **/
    public static final String COLUMN_NAME_SPECIAL_FEATURES = "special_features";

    public static final String ATTRIBUTE_NAME_SPECIAL_FEATURES = "specialFeatures";

    /**
     * A constant for special feature of <i>trailers</i>.
     */
    public static final String SPECIAL_FEATURE_TRAILERS = "Trailers";

    /**
     * A constant for special feature of <i>commentaries</i>.
     */
    public static final String SPECIAL_FEATURE_COMMENTARIES = "Commentaries";

    /**
     * A constant for special feature of <i>deleted scenes</i>.
     */
    public static final String SPECIAL_FEATURE_DELETED_SCENES = "Deleted Scenes";

    /**
     * A constant for special feature of <i>behind the scenes</i>.
     */
    public static final String SPECIAL_FEATURE_BEHIND_THE_SCENES = "Behind the Scenes";

    /**
     * Constants for {@link #ATTRIBUTE_NAME_SPECIAL_FEATURES specialFeatures} attribute.
     */
    public enum SpecialFeature implements CustomValuedEnum<SpecialFeature, String> {

        /**
         * Constant for {@link #SPECIAL_FEATURE_TRAILERS}.
         */
        TRAILERS(SPECIAL_FEATURE_TRAILERS),

        /**
         * Constant for {@link #SPECIAL_FEATURE_COMMENTARIES}.
         */
        COMMENTARIES(SPECIAL_FEATURE_COMMENTARIES),

        /**
         * Constant for {@link #SPECIAL_FEATURE_DELETED_SCENES}.
         */
        DELETED_SCENES(SPECIAL_FEATURE_DELETED_SCENES),

        /**
         * Constant for {@link #SPECIAL_FEATURE_BEHIND_THE_SCENES}.
         */
        BEHIND_THE_SCENES(SPECIAL_FEATURE_BEHIND_THE_SCENES);

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Creates a new instance with specified database column value.
         *
         * @param databaseColumn a value for {@code databaseColumn}.
         */
        SpecialFeature(final String databaseColumn) {
            this.databaseColumn = requireNonNull(databaseColumn);
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * {@inheritDoc}
         *
         * @return {@inheritDoc}
         */
        @Override
        public String getDatabaseColumn() {
            return databaseColumn;
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The database column value of this constant.
         */
        final String databaseColumn;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A converter class for converting between a set of {@link SpecialFeature}s and comma separated columns.
     */
    @Converter
    @Deprecated // forRemoval = true
    public static class SpecialFeaturesAttributeConverter
            extends CustomValuedEnumSetJoinedStringAttributeConverter<SpecialFeature> {

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Creates a new instance.
         */
        public SpecialFeaturesAttributeConverter() {
            super(SpecialFeature.class, ",");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 7/13/2019 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_CATEGORIES = "categories";

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 7/13/2019 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_ACTORS = "actors";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Film() {
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
               + "title=" + title
               + ",description=" + description
               + ",releaseYear=" + releaseYear
               + ",language=" + language
               + ",originalLanguage=" + originalLanguage
               + ",rentalDuration=" + rentalDuration
               + ",rentalRate=" + rentalRate
               + ",length=" + length
               + ",replacementCost=" + replacementCost
               + ",rating=" + rating
               + ",specialFeatures=" + specialFeatures
               + "}";
    }

    // TODO: 2019-07-09 equals/hashCode???

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Indicates whether this film is dubbed which means the current value of {@value #ATTRIBUTE_NAME_LANGUAGE}
     * attribute is different from the value of {@value #ATTRIBUTE_NAME_ORIGINAL_LANGUAGE} attribute. Note that a film
     * whose {@link #ATTRIBUTE_NAME_ORIGINAL_LANGUAGE} is {@code null} is not dubbed.
     *
     * @return {@code true} if this film is dubbed; {@code false} otherwise.
     */
    @Transient
    public boolean isDubbed() {
        // TODO: 2019-07-09 implement
        throw new UnsupportedOperationException("not implemented yet");
    }

    // ----------------------------------------------------------------------------------------------------------- title
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    // ----------------------------------------------------------------------------------------------------- description
    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    // ----------------------------------------------------------------------------------------------------- releaseYear
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    // -------------------------------------------------------------------------------------------------------- language
    public Language getLanguage() {
        return language;
    }

    public void setLanguage(final Language language) {
        if (this.language != null) {
            final boolean removedFromOldLanguage = this.language.getFilms().remove(this);
        }
        this.language = language;
        if (this.language != null && !this.language.getFilms().contains(this)) {
            final boolean addedToNewLanguage = this.language.addFilm(this);
        }
    }

    // ------------------------------------------------------------------------------------------------ originalLanguage
    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(final Language originalLanguage) {
        if (this.originalLanguage != null) {
            final boolean removedFromOldOriginalLanguage = this.originalLanguage.getFilmsRecorded().remove(this);
        }
        this.originalLanguage = originalLanguage;
        if (this.originalLanguage != null && !this.originalLanguage.getFilmsRecorded().contains(this)) {
            final boolean addedToNewOriginalLanguage = this.originalLanguage.addFilmRecorded(this);
        }
    }

    // -------------------------------------------------------------------------------------------------- rentalDuration
    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(final int rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    //@Transient
    public Duration getRentalDurationInJavaTime() {
        return Duration.of(getRentalDuration(), ChronoUnit.DAYS);
    }

    public void setRentalDurationInJavaTime(final Duration rentalDurationInJavaTime) {
        if (rentalDurationInJavaTime == null) {
            throw new NullPointerException("rentalDurationInJavaTime is null");
        }
        setRentalDuration(toIntExact(rentalDurationInJavaTime.toDays()));
    }

    // ------------------------------------------------------------------------------------------------------ rentalRate
    public BigDecimal getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(final BigDecimal rentalRate) {
        this.rentalRate = rentalRate;
    }

    // ---------------------------------------------------------------------------------------------------------- length
    public Integer getLength() {
        return length;
    }

    public void setLength(final Integer length) {
        this.length = length;
    }

    //@Transient
    public Duration getLengthAsDuration() {
        return ofNullable(getLength()).map(v -> Duration.of(v, ChronoUnit.MINUTES)).orElse(null);
    }

    public void setLengthAsDuration(final Duration lengthAsDuration) {
        setLength(ofNullable(lengthAsDuration).map(v -> toIntExact(v.toMinutes())).orElse(null));
    }

    // ------------------------------------------------------------------------------------------------- replacementCost
    public BigDecimal getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(final BigDecimal replacementCost) {
        this.replacementCost = replacementCost;
    }

    // ---------------------------------------------------------------------------------------------------------- rating
    public Rating getRating() {
        return rating;
    }

    public void setRating(final Rating rating) {
        this.rating = rating;
    }

    // ------------------------------------------------------------------------------------------------- specialFeatures
    public String getSpecialFeatures() {
        return specialFeatures;
    }

    // TODO: 2019-07-23 private!!!
    public void setSpecialFeatures(final String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    /**
     * Returns special features of this film as a set.
     *
     * @return the special feature of this film as a set; {@code null} if {@link #ATTRIBUTE_NAME_SPECIAL_FEATURES
     * specialFeatures} attribute is {@code null}.
     */
    public EnumSet<SpecialFeature> getSpecialFeaturesAsSet() {
        return ofNullable(getSpecialFeatures())
                .map(v -> Arrays.stream(v.split(",")).map(SpecialFeature::valueOf)
                        .collect(toCollection(() -> EnumSet.noneOf(SpecialFeature.class)))).orElse(null);
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_SPECIAL_FEATURES specialFeatures} attribute with specified
     * value.
     *
     * @param specialFeaturesAsSet new value for {@link #ATTRIBUTE_NAME_SPECIAL_FEATURES specialFeatures} attribute.
     */
    public void setSpecialFeaturesAsSet(final EnumSet<SpecialFeature> specialFeaturesAsSet) {
        setSpecialFeatures(ofNullable(specialFeaturesAsSet)
                                   .map(a -> a.stream().map(SpecialFeature::getDatabaseColumn).collect(joining(",")))
                                   .orElse(null));
    }

    // ------------------------------------------------------------------------------------------------------ categories
    // TODO: 7/13/2019 remove!!!
    @Deprecated // forRemoval = true
    public Set<Category> getCategories() {
        if (categories == null) {
            categories = new HashSet<>();
        }
        return categories;
    }

    // TODO: 7/13/2019 remove!!!
    @Deprecated // forRemoval = true
    public boolean addCategory(final Category category) {
        if (category == null) {
            throw new NullPointerException("category is null");
        }
        final boolean categoryAdded = getCategories().add(category); // TODO: 2019-07-17 equals/hashCode???
        if (!category.getFilms().contains(this)) {
            final boolean addedToCategory = category.addFilm(this);
        }
        return categoryAdded;
    }

    // ---------------------------------------------------------------------------------------------------------- actors
    // TODO: 7/13/2019 remove!!!
    @Deprecated // forRemoval = true
    public Set<Actor> getActors() {
        if (actors == null) {
            actors = new HashSet<>();
        }
        return actors;
    }

    // TODO: 7/13/2019 remove!!!
    @Deprecated // forRemoval = true
    public boolean addActor(final Actor actor) {
        if (actor == null) {
            throw new NullPointerException("actor is null");
        }
        final boolean actorAdded = getActors().add(actor); // TODO: 2019-07-23 equals/hashCode???
        if (!actor.getFilms().contains(this)) {
            final boolean addedToActor = actor.addFilm(this);
        }
        return actorAdded;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(max = SIZE_MAX_TITLE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TITLE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_TITLE)
    private String title;

    @Size(max = SIZE_MAX_DESCRIPTION)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_DESCRIPTION)
    @NamedAttribute(ATTRIBUTE_NAME_DESCRIPTION)
    private String description;

    @Max(MAX_RELEASE_YEAR)
    @Min(MIN_RELEASE_YEAR)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RELEASE_YEAR)
    @NamedAttribute(ATTRIBUTE_NAME_RELEASE_YEAR)
    private Integer releaseYear;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_LANGUAGE_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_LANGUAGE)
    private Language language;

    @ManyToOne(optional = true)
    @JoinColumn(name = COLUMN_NAME_ORIGINAL_LANGUAGE_ID)
    @NamedAttribute(ATTRIBUTE_NAME_ORIGINAL_LANGUAGE)
    private Language originalLanguage;

    @Max(MAX_RENTAL_DURATION)
    @Min(MIN_RENTAL_DURATION)
    @NotNull
    @Column(name = COLUMN_NAME_RENTAL_DURATION, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_RENTAL_DURATION)
    private int rentalDuration; // +

    @DecimalMax(DECIMAL_MAX_RENTAL_RATE)
    @DecimalMin(DECIMAL_MIN_RENTAL_RATE)
    @Digits(integer = DIGITS_INTEGER_RENTAL_RATE, fraction = DIGITS_FRACTION_RENTAL_RATE)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RENTAL_RATE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_RENTAL_RATE)
    private BigDecimal rentalRate;

    @Max(MAX_LENGTH)
    @Min(MIN_LENGTH)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_LENGTH, nullable = true)
    @NamedAttribute(ATTRIBUTE_NAME_LENGTH)
    private Integer length; // +

    @DecimalMax(DECIMAL_MAX_REPLACEMENT_COST)
    @DecimalMin(DECIMAL_MIN_REPLACEMENT_COST)
    @Digits(integer = DIGITS_INTEGER_REPLACEMENT_COST, fraction = DIGITS_FRACTION_REPLACEMENT_COST)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_REPLACEMENT_COST, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_REPLACEMENT_COST)
    private BigDecimal replacementCost;

    //@Enumerated(EnumType.ORDINAL)
    //@Enumerated(EnumType.STRING)
    @Convert(converter = RatingAttributeConverter.class)
    @Column(name = COLUMN_NAME_RATING, nullable = true)
    @NamedAttribute(ATTRIBUTE_NAME_RATING)
    private Rating rating;

    //@Convert(converter = SpecialFeaturesAttributeConverter.class)
    @Column(name = COLUMN_NAME_SPECIAL_FEATURES, nullable = true)
    @NamedAttribute(ATTRIBUTE_NAME_SPECIAL_FEATURES)
    //private Set<SpecialFeature> specialFeatures;
    private String specialFeatures;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-11 remove!!!
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
            targetEntity = Category.class
    )
    @JoinTable(name = FilmCategory.TABLE_NAME,
               joinColumns = {
                       @JoinColumn(name = FilmCategory.COLUMN_NAME_FILM_ID, referencedColumnName = COLUMN_NAME_FILM_ID)
               },
               inverseJoinColumns = {
                       @JoinColumn(name = FilmCategory.COLUMN_NAME_CATEGORY_ID,
                                   referencedColumnName = Category.COLUMN_NAME_CATEGORY_ID)
               }
    )
    @NamedAttribute(ATTRIBUTE_NAME_CATEGORIES)
    private Set<Category> categories;

    // TODO: 2019-07-11 remove!!!
    @Deprecated
    @ManyToMany(mappedBy = Actor.ATTRIBUTE_NAME_FILMS)
    @NamedAttribute(ATTRIBUTE_NAME_ACTORS)
    private Set<Actor> actors;
}
