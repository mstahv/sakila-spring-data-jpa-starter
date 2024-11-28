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
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.Rental.COLUMN_NAME_RENTAL_ID;
import static com.example.demo.sakilaentities.Rental.TABLE_NAME;

@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_RENTAL_ID, nullable = false))
@Entity
@Table(name = TABLE_NAME)
public class Rental extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "rental";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RENTAL_ID = "rental_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RENTAL_DATE = "rental_date";

    public static final String ATTRIBUTE_NAME_RENTAL_DATE = "rentalDate";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_INVENTORY_ID = "inventory_id";

    public static final String ATTRIBUTE_NAME_INVENTORY = "inventory";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_CUSTOMER_ID = "customer_id";

    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_RETURN_DATE = "return_date";

    public static final String ATTRIBUTE_NAME_RETURN_DATE = "returnDate";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_STAFF_ID = "staff_id";

    public static final String ATTRIBUTE_NAME_STAFF = "staff";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Rental() {
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
               + "rentalDate=" + rentalDate
               + ",inventory=" + inventory
               + ",customer=" + customer
               + ",returnDate=" + returnDate
               + ",staff=" + staff
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------
    @AssertTrue
    private boolean isReturnDateAfterRentalDate() {
        return returnDate == null || returnDate.isAfter(rentalDate);
    }

    // ------------------------------------------------------------------------------------------------------ rentalDate
    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(final LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    // ------------------------------------------------------------------------------------------------------- inventory
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(final Inventory inventory) {
        this.inventory = inventory;
    }

    // -------------------------------------------------------------------------------------------------------- customer
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    // ------------------------------------------------------------------------------------------------------ returnDate
    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    // ----------------------------------------------------------------------------------------------------------- staff
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(final Staff staff) {
        this.staff = staff;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @PastOrPresent
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_RENTAL_DATE, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_RENTAL_DATE)
    private LocalDateTime rentalDate;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_INVENTORY_ID, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_INVENTORY)
    private Inventory inventory;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CUSTOMER)
    private Customer customer;

    @Basic(optional = true)
    @Column(name = COLUMN_NAME_RETURN_DATE, nullable = true, updatable = true)
    @NamedAttribute(ATTRIBUTE_NAME_RETURN_DATE)
    private LocalDateTime returnDate;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_STAFF_ID, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_STAFF)
    private Staff staff;
}
