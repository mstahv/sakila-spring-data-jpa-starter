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

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_FIRST_NAME;
import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_LAST_NAME;

/**
 * An entity class for binding {@value TABLE_NAME} table.
 */
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = Customer.COLUMN_NAME_CUSTOMER_ID))
@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer extends BaseEntity implements FullNamed {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity class. The value is {@value}.
     */
    public static final String TABLE_NAME = "customer";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CUSTOMER_ID = "customer_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_STORE_ID = "store_id";

    public static final String ATTRIBUTE_NAME_STORE = "store";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EMAIL = "email";

    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    public static final int SIZE_MAX_EMAIL = 50;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ADDRESS_ID = "address_id";

    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ACTIVE = "active";

    public static final String ATTRIBUTE_NAME_ACTIVE = "active";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Customer() {
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
               + "store=" + store
               + "firstName=" + firstName
               + ",lastName=" + lastName
               + ",email=" + email
               + ",address=" + address
               + ",active=" + active
               + "}";
    }

    // TODO: 2019-07-10 equals/hashCode???

    // ----------------------------------------------------------------------------------------------------------- store
    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    // ------------------------------------------------------------------------------------------------------- firstName
    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    // -------------------------------------------------------------------------------------------------------- lastName
    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    // ----------------------------------------------------------------------------------------------------------- email
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    // --------------------------------------------------------------------------------------------------------- address
    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    // ---------------------------------------------------------------------------------------------------------- active
    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_STORE)
    private Store store;

    @Size(min = SIZE_MIN_FIRST_NAME, max = SIZE_MAX_FIRST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_FIRST_NAME, nullable = false, length = SIZE_MAX_FIRST_NAME)
    @NamedAttribute(ATTRIBUTE_NAME_FIRST_NAME)
    private String firstName;

    @Size(min = SIZE_MIN_LAST_NAME, max = SIZE_MAX_LAST_NAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_LAST_NAME, nullable = false, length = SIZE_MAX_LAST_NAME)
    @NamedAttribute(ATTRIBUTE_NAME_LAST_NAME)
    private String lastName;

    @Size(max = SIZE_MAX_EMAIL)
    @Basic
    @Column(name = COLUMN_NAME_EMAIL)
    @NamedAttribute(ATTRIBUTE_NAME_EMAIL)
    private String email;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_ADDRESS_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ADDRESS)
    private Address address;

    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACTIVE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ACTIVE)
    private boolean active;
}
