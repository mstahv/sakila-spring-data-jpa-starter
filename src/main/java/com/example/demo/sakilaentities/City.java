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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static java.util.Comparator.comparing;

/**
 * An entity class for binding {@value #TABLE_NAME} table.
 * <blockquote>
 * The {@code city} table contains a list of cities.
 * <p>
 * The {@code city} table is referred to by a foreign key in the {@link Address address} table and refers to the {@link
 * Country country} table using a foreign key.
 * </blockquote>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-city.html">The city Table (Sakila Sample
 * Database, MySQL Documentation)</a>
 */
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = City.COLUMN_NAME_CITY_ID, nullable = false))
@Entity
@Table(name = City.TABLE_NAME)
public class City extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity class. The value is {@value}.
     */
    public static final String TABLE_NAME = "city";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of this table. The value is {@value}.
     * <blockquote>
     * A surrogate primary key used to uniquely identify each city in the table.
     * </blockquote>
     * {@code SMALLINT(5) PK NN UN AI}
     */
    public static final String COLUMN_NAME_CITY_ID = "city_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@link #ATTRIBUTE_NAME_CITY} attribute. The value is {@value}.a
     * <blockquote>
     * The name of the city.
     * </blockquote>
     * {@code VARCHAR(50) NN}
     */
    public static final String COLUMN_NAME_CITY = "city";

    public static final String ATTRIBUTE_NAME_CITY = "city";

    public static final int SIZE_MIN_CITY = 0; // TODO: 2019-07-12 empty???

    public static final int SIZE_MAX_CITY = 50;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column for {@link #ATTRIBUTE_NAME_COUNTRY} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key identifying the country that the city belongs to.
     * </blockquote>
     * {@code SMALLINT(5) NN UN}
     */
    public static final String COLUMN_NAME_COUNTRY_ID = "country_id";

    /**
     * The entity column name for {@link #COLUMN_NAME_COUNTRY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_COUNTRY = "country";

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_ADDRESSES = "addresses";

    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    public static final int SIZE_MIN_ADDRESSES = 0;

    // TODO: 2019-07-10  remove!!!
    @Deprecated // forRemoval = true
    public static final int SIZE_MAX_ADDRESSES = Integer.MAX_VALUE; // TODO: 2019-07-17 what???

    // -----------------------------------------------------------------------------------------------------------------
    public static final Comparator<City> COMPARING_CITY = comparing(City::getCity);

    public static Comparator<City> comparingCity(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_CITY : COMPARING_CITY.reversed();
    }

    public static final Comparator<City> COMPARING_CITY_IGNORE_CASE = (c1, c2) -> c1.city.compareToIgnoreCase(c2.city);

    public static Comparator<City> comparingCityIgnoreCase(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_CITY_IGNORE_CASE : COMPARING_CITY_IGNORE_CASE.reversed();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public City() {
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
               + "city=" + city
               + ",country=" + country
               + "}";
    }

    // TODO: 2019-07-12 equals/hashCode ???

    // ------------------------------------------------------------------------------------------------------------ city

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_CITY} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_CITY} attribute.
     */
    public String getCity() {
        return city;
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_CITY} attribute with specified value.
     *
     * @param city new value for {@link #ATTRIBUTE_NAME_CITY} attribute.
     */
    public void setCity(final String city) {
        this.city = city;
    }

    // --------------------------------------------------------------------------------------------------------- country
    public Country getCountry() {
        return country;
    }

    public void setCountry(final Country country) {
        if (this.country != null) {
            final boolean removedFromOldCountry = this.country.getCities().remove(this); // TODO: 2019-07-12 equals???
        }
        this.country = country;
        if (this.country != null && !this.country.getCities().contains(this)) { // TODO: 2019-07-12 equals???
            final boolean addedToNewCountry = this.country.addCity(this);
        }
    }

    // ------------------------------------------------------------------------------------------------------- addresses
    // TODO: 2019-07-10 remove!!!
    @Deprecated
    public List<Address> getAddresses() {
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        return addresses;
    }

    // TODO: 2019-07-10 remove!!!
    @Deprecated
    public void addAddress(final Address address) {
        final boolean addressAdded = getAddresses().add(address);
        if (address.getCity() != this) {
            address.setCity(this);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(min = SIZE_MIN_CITY, max = SIZE_MAX_CITY)
    @NotNull // TODO: 2019-07-10 @NotBlank???
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_CITY, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CITY)
    private String city;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_COUNTRY_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_COUNTRY)
    private Country country;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    @OneToMany(mappedBy = Address.ATTRIBUTE_NAME_CITY)
    @NamedAttribute(ATTRIBUTE_NAME_ADDRESSES)
    private @Size(min = SIZE_MIN_ADDRESSES, max = SIZE_MAX_ADDRESSES) List<@NotNull Address> addresses;
}
