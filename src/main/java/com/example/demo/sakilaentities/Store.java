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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.Store.COLUMN_NAME_STORE_ID;
import static com.example.demo.sakilaentities.Store.TABLE_NAME;

/**
 * An entity class for {@value #TABLE_NAME} table.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_STORE_ID))
@Entity
@Table(name = TABLE_NAME)
public class Store extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The target table name of this entity class. The value is {@value}.
     */
    public static final String TABLE_NAME = "store";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The primary key column name of this entity. The value is {@value} column.
     * <blockquote>
     * A surrogate primary key that uniquely identifies the store.
     * </blockquote>
     * {@code TINYINT(5) PK NN UN AI}
     */
    public static final String COLUMN_NAME_STORE_ID = "store_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value ATTRIBUTE_NAME_MANAGER} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key identifying the manager of this store.
     * </blockquote>
     * {@code TINYINT(3) NN UQ UN}
     */
    public static final String COLUMN_NAME_MANAGER_STAFF_ID = "manager_staff_id";

    /**
     * The entity attribute name for {@value #COLUMN_NAME_MANAGER_STAFF_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_MANAGER = "manager";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_ADDRESS} attribute. The value is {@value}.
     * <blockquote>
     * A foreign key identifying the address of this store.
     * </blockquote>
     * {@code SMALLINT(5) NN UN}
     */
    public static final String COLUMN_NAME_ADDRESS_ID = "address_id";

    /**
     * The attribute name for {@value #COLUMN_NAME_ADDRESS_ID} column. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_STAFFS = "staffs";

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    public static final String ATTRIBUTE_NAME_CUSTOMERS = "customers";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Store() {
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
               + "manager=" + manager
               + ",address=" + address
               + "}";
    }

    // TODO: 2019-07-10 equals/hashCode???

    // --------------------------------------------------------------------------------------------------------- manager

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    public Staff getManager() {
        return manager;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     *
     * @param manager new value for {@value #ATTRIBUTE_NAME_MANAGER} attribute.
     */
    public void setManager(final Staff manager) {
        this.manager = manager;
    }

    // --------------------------------------------------------------------------------------------------------- address

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute with specified value.
     *
     * @param address new current value of {@value #ATTRIBUTE_NAME_ADDRESS} attribute.
     */
    public void setAddress(final Address address) {
        this.address = address;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    public Set<Staff> getStaffs() {
        if (staffs == null) {
            staffs = new HashSet<>();
        }
        return staffs;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    public Set<Customer> getCustomers() {
        if (customers == null) {
            customers = new HashSet<>();
        }
        return customers;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_MANAGER_STAFF_ID, nullable = false, unique = true)
    @NamedAttribute(ATTRIBUTE_NAME_MANAGER)
    private Staff manager;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_ADDRESS_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ADDRESS)
    private Address address;

    // -----------------------------------------------------------------------------------------------------------------
    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    @OneToMany(mappedBy = Staff.ATTRIBUTE_NAME_STORE)
    @NamedAttribute(ATTRIBUTE_NAME_STAFFS)
    private Set<Staff> staffs;

    // TODO: 2019-07-10 remove!!!
    @Deprecated // forRemoval = true
    @OneToMany(mappedBy = Customer.ATTRIBUTE_NAME_STORE)
    @NamedAttribute(ATTRIBUTE_NAME_STAFFS)
    private Set<Customer> customers;
}
