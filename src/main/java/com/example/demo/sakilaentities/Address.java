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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;

import static com.example.demo.sakilaentities.Address.COLUMN_NAME_ADDRESS_ID;
import static com.example.demo.sakilaentities.Address.TABLE_NAME;
import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static java.util.Comparator.comparing;

/**
 * An entity class for binding {@value #TABLE_NAME} table.
 * <blockquote>
 * The address table contains address information for customers, staff, and stores.
 * <p>
 * The address table primary key appears as a foreign key in the <code>{@link Customer customer}</code>, <code>{@link
 * Staff staff}</code>, and <code>{@link Store store}</code> tables.
 * </blockquote>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see <a href="https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-address.html">The address Table (Sakila
 * Sample Database, Developer Zone, MySQL)</a>
 */
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_ADDRESS_ID, nullable = false))
@Entity
@Table(name = TABLE_NAME)
public class Address extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the target table of this entity class. The value is {@value}.
     */
    public static final String TABLE_NAME = "address";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of this entity. The value is {@value}.
     * <blockquote>
     * A surrogate primary key used to uniquely identify each address in the table.
     * </blockquote>
     * {@code SMALLINT(5) PK NN UN AI}
     */
    public static final String COLUMN_NAME_ADDRESS_ID = "address_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_ADDRESS}. The value is {@value}.
     * <blockquote>
     * The first line of an address.
     * </blockquote>
     * {@code VARCHAR(50) NN}
     */
    public static final String COLUMN_NAME_ADDRESS = "address";

    /**
     * The length of {@value #COLUMN_LENGTH_ADDRESS} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS = 50;

    /**
     * The entity attribute name for {@value #COLUMN_NAME_ADDRESS} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    /**
     * The minimum size of {@value #ATTRIBUTE_NAME_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_ADDRESS = 0; // TODO: 2019-07-12 empty???

    /**
     * The maximum size of {@value #ATTRIBUTE_NAME_ADDRESS} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ADDRESS = COLUMN_LENGTH_ADDRESS;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@link #ATTRIBUTE_NAME_ADDRESS2} attribute. The value is {@value}.
     * <blockquote>
     * An optional second line of an address.
     * </blockquote>
     * {@code VARCHAR(50) NULL}
     */
    public static final String COLUMN_NAME_ADDRESS2 = "address2";

    /**
     * The length of {@value #COLUMN_NAME_ADDRESS2} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_ADDRESS2 = 50;

    /**
     * The entity attribute name for {@link #COLUMN_NAME_ADDRESS2} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS2 = "address2";

    /**
     * The minimum size of {@link #ATTRIBUTE_NAME_ADDRESS2} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_ADDRESS2 = 0; // TODO: 2019-07-12 empty???

    /**
     * The maximum size of {@link #ATTRIBUTE_NAME_ADDRESS2} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_ADDRESS2 = COLUMN_LENGTH_ADDRESS2;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_DISTRICT} attribute. The value is {@value}.
     * <blockquote>
     * The region of an address, this may be a state, province, prefecture, etc.
     * </blockquote>
     * {@code VARCHAR(20) NN}
     */
    public static final String COLUMN_NAME_DISTRICT = "district";

    /**
     * The length of {@value #COLUMN_NAME_DISTRICT} column. The value is {@value}.
     */
    public static final int COLUMN_LENGTH_DISTRICT = 20;

    /**
     * The entity name for {@value #COLUMN_NAME_DISTRICT} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_DISTRICT = "district";

    /**
     * The minimum size of {@value #ATTRIBUTE_NAME_DISTRICT} attribute. The value is {@value}.
     */
    public static final int SIZE_MIN_DISTRICT = 0; // TODO: 2019-07-12 empty???

    /**
     * The maximum size of {@value #ATTRIBUTE_NAME_DISTRICT} attribute. The value is {@value}.
     */
    public static final int SIZE_MAX_DISTRICT = COLUMN_LENGTH_DISTRICT;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_CITY} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key pointing to the <code>{@link City city}</code> table.
     * </blockquote>
     * {@code SMALLINT(5) NN UN}
     */
    public static final String COLUMN_NAME_CITY_ID = "city_id";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_CITY_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_CITY = "city";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_POSTAL_CODE} attribute. The value is {@value}.
     * <blockquote>
     * The postal code or ZIP code of the address (where applicable).
     * </blockquote>
     * {@code VARCHAR(10) NULL}
     */
    public static final String COLUMN_NAME_POSTAL_CODE = "postal_code";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_POSTAL_CODE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_POSTAL_CODE = "postalCode";

    public static final int SIZE_MAX_POSTAL_CODE = 10;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_PHONE} attribute. The value is {@value}.
     * <blockquote>
     * The telephone number for the address.
     * </blockquote>
     * {@code VARCHAR(20) NN}
     */
    public static final String COLUMN_NAME_PHONE = "phone";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_PHONE} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_PHONE = "phone";

    public static final int SIZE_MAX_PHONE = 10;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_LOCATION} attribute. The value is {@value}.
     * <blockquote>
     * A Geometry column with a spatial index on it.
     * </blockquote>
     * {@code GEOMETRY NN}
     *
     * @see <a href="https://dev.mysql.com/doc/refman/5.7/en/gis-data-formats.html#gis-wkb-format">Well-Known Binary
     * (WKB) Format (Supported Spatial Data Formats, MySQL Reference Manual)</a>
     */
    public static final String COLUMN_NAME_LOCATION = "location";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_LOCATION} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_LOCATION = "location";

    public static final int SIZE_LOCATION_FOR_POINT = 21; // 1 + 4 + 8 + 8

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator for comparing addresses with {@link #ATTRIBUTE_NAME_DISTRICT district} attributes.
     */
    public static final Comparator<Address> COMPARING_DISTRICT = comparing(Address::getDistrict);

    public static final Comparator<Address> COMPARING_DISTRICT_IGNORE_CASE
            = (a1, a2) -> a1.district.compareToIgnoreCase(a2.district);

    public static final Comparator<Address> COMPARING_ADDRESS = comparing(Address::getAddress);

    public static final Comparator<Address> COMPARING_ADDRESS_IGNORE_CASE
            = (a1, a2) -> a1.address.compareToIgnoreCase(a2.address);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Address() {
        super();
    }

    // --------------------------------------------------------------------------------------------------------- address

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_ADDRESS address} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_ADDRESS address} attribute.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_ADDRESS address} attribute with specified value.
     *
     * @param address new value for {@link #ATTRIBUTE_NAME_ADDRESS address} attribute.
     */
    public void setAddress(final String address) {
        this.address = address;
    }

    // -------------------------------------------------------------------------------------------------------- address2

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_ADDRESS2 address2} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_ADDRESS2 address2} attribute.
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_ADDRESS2 address2} attribute with specified value.
     *
     * @param address2 new value for {@link #ATTRIBUTE_NAME_ADDRESS2 address2} attribute.
     */
    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    // -------------------------------------------------------------------------------------------------------- district
    public String getDistrict() {
        return district;
    }

    public void setDistrict(final String district) {
        this.district = district;
    }

    // ------------------------------------------------------------------------------------------------------------ city
    public City getCity() {
        return city;
    }

    public void setCity(final City city) {
        if (this.city != null) {
            final boolean removedFromOldCity = this.city.getAddresses().remove(this); // TODO: 2019-07-12 equality???
        }
        this.city = city;
        if (this.city != null && !this.city.getAddresses().contains(this)) { // TODO: 2019-07-12 equality???
            this.city.addAddress(this);
        }
    }

    // ------------------------------------------------------------------------------------------------------ postalCode

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_POSTAL_CODE postalCode} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_POSTAL_CODE postalCode} attribute.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_POSTAL_CODE postalCode} attribute with specified value.
     *
     * @param postalCode new value for {@link #ATTRIBUTE_NAME_POSTAL_CODE postalCode} attribute.
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    // ----------------------------------------------------------------------------------------------------------- phone

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_PHONE} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_PHONE} attribute
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_PHONE} attribute with specified value.
     *
     * @param phone new value of {@link #ATTRIBUTE_NAME_PHONE} attribute
     */
    public void setPhone(final String phone) {
        this.phone = phone;
    }

    // -------------------------------------------------------------------------------------------------------- location

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_LOCATION} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_LOCATION} attribute.
     */
    // TODO: 2019-07-23 private!!!
    public byte[] getLocation() {
        // TODO: 2019-07-12 copy!!!
        return location;
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_LOCATION} attribute with specified value.
     *
     * @param location new value for {@link #ATTRIBUTE_NAME_LOCATION} attribute.
     */
    // TODO: 2019-07-23 private!!!
    public void setLocation(final byte[] location) {
        // TODO: 2019-07-12 copy!!!
        this.location = location;
    }

    /**
     * Parses the current value of {@link #ATTRIBUTE_NAME_LOCATION} attribute as a {@code Point} in <a
     * href="https://dev.mysql.com/doc/refman/5.7/en/gis-data-formats.html#gis-wkb-format">Well-Known Binary (WKB)
     * Format</a> and applies two coordinates to specified function.
     *
     * @param function the function to be applied.
     * @param <R>      function result type parameter
     * @return the result of the {@code function}.
     */
    public <R> R applyLocationAsPoint(final BiFunction<? super Double, ? super Double, ? extends R> function) {
        final byte[] location = getLocation();
        if (location == null) {
            throw new IllegalStateException(
                    "the value of '" + ATTRIBUTE_NAME_LOCATION + "' attribute is currently null");
        }
        if (location.length != SIZE_LOCATION_FOR_POINT) {
            throw new IllegalStateException(
                    "the length(" + location.length + ") of the current value of '" + ATTRIBUTE_NAME_LOCATION
                    + "' attribute != " + SIZE_LOCATION_FOR_POINT);
        }
        final ByteBuffer buffer = ByteBuffer.wrap(location);
        buffer.order(buffer.get() == 0x00 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        final int type = buffer.getInt();
        if (type != 0x01) {
            throw new IllegalArgumentException("location.type(" + type + ") is not for Point(1)");
        }
        final double x = buffer.getDouble();
        final double y = buffer.getDouble();
        return function.apply(x, y);
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_LOCATION} attribute with a value of point.
     *
     * @param x     a value for x coordinate.
     * @param y     a value for y coordinate.
     * @param order a byte order.
     */
    public void setLocationAsPoint(final double x, final double y, final ByteOrder order) {
        final ByteBuffer buffer = ByteBuffer.allocate(SIZE_LOCATION_FOR_POINT).order(order);
        buffer.put(order == ByteOrder.BIG_ENDIAN ? (byte) 0x00 : (byte) 0x01);
        buffer.putInt(0x01); // Point
        buffer.putDouble(x);
        buffer.putDouble(y);
        setLocation(buffer.array());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Size(min = SIZE_MIN_ADDRESS, max = SIZE_MAX_ADDRESS)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ADDRESS, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ADDRESS)
    private String address;

    @Size(min = SIZE_MIN_ADDRESS2, max = SIZE_MAX_ADDRESS2)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_ADDRESS2)
    @NamedAttribute(ATTRIBUTE_NAME_ADDRESS2)
    private String address2;

    @Size(min = SIZE_MIN_DISTRICT, max = SIZE_MAX_DISTRICT)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_DISTRICT)
    @NamedAttribute(ATTRIBUTE_NAME_DISTRICT)
    private String district;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_CITY_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CITY)
    private City city;

    @Size(max = SIZE_MAX_POSTAL_CODE)
    @Basic(optional = true)
    @Column(name = COLUMN_NAME_POSTAL_CODE, nullable = true)
    @NamedAttribute(ATTRIBUTE_NAME_POSTAL_CODE)
    private String postalCode;

    @Size(max = SIZE_MAX_PHONE)
    @NotNull
    @Column(name = COLUMN_NAME_PHONE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_PHONE)
    private String phone;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LOCATION, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_LOCATION)
    private byte[] location; // +
}
