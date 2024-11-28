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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.Payment.COLUMN_NAME_PAYMENT_ID;

@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_PAYMENT_ID, nullable = false))
@Entity
@Table(name = Payment.TABLE_NAME)
public class Payment extends BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "payment";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_PAYMENT_ID = "payment_id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@link #ATTRIBUTE_NAME_CUSTOMER} attribute. The value is {@value}.
     * <blockquote>
     * The customer whose balance the payment is being applied to. This is a foreign key reference to the {@link
     * Customer customer} table.
     * </blockquote>
     * {@code SMALLINT(5) NN UN}
     */
    public static final String COLUMN_NAME_CUSTOMER_ID = "customer_id";

    public static final String ATTRIBUTE_NAME_CUSTOMER = "customer";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@link #ATTRIBUTE_NAME_STAFF} attribute. The value is {@value}.
     * <blockquote>
     * The staff member who processed the payment. This is a foreign key reference to the {@code staff} table.
     * </blockquote>
     * {@code TINYINT(3) NN UN}
     */
    public static final String COLUMN_NAME_STAFF_ID = "staff_id";

    public static final String ATTRIBUTE_NAME_STAFF = "staff";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_RENTAL} attribute. The value is {@value}.
     * <blockquote>
     * The rental that the payment is being applied to. This is optional because some payments are for outstanding fees
     * and may not be directly related to a rental.
     * </blockquote>
     * {@code INT(11) NULL}
     */
    public static final String COLUMN_NAME_RENTAL_ID = "rental_id";

    public static final String ATTRIBUTE_NAME_RENTAL = "rental";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_AMOUNT} attribute. The value is {@value}.
     * <blockquote>
     * The amount of the payment.
     * </blockquote>
     * {@code DECIMAL(5,2) NN}
     */
    public static final String COLUMN_NAME_AMOUNT = "amount";

    public static final String ATTRIBUTE_NAME_AMOUNT = "amount";

    public static final int DIGITS_INTEGER_AMOUNT = 5;

    public static final int DIGITS_FRACTION_AMOUNT = 2;

    public static final String DECIMAL_MIN_AMOUNT = "00000.00";

    public static final String DECIMAL_MAX_AMOUNT = "99999.99";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@value #ATTRIBUTE_NAME_PAYMENT_DATE} attribute. The value is {@value}.
     * <blockquote>
     * The date the payment was processed.
     * </blockquote>
     * {@code DATETIME NN}
     */
    public static final String COLUMN_NAME_PAYMENT_DATE = "payment_date";

    public static final String ATTRIBUTE_NAME_PAYMENT_DATE = "paymentDate";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance.
     */
    public Payment() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @PrePersist
    private void onPrePersist() {
        if (rental != null) {
            if (staff == null) {
                staff = rental.getStaff();
            }
            if (customer == null) {
                customer = rental.getCustomer();
            }
            if (paymentDate == null) {
                paymentDate = rental.getRentalDate();
            }
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "customer=" + customer
               + ",staff=" + staff
               + ",rental=" + rental
               + ",amount=" + amount
               + ",paymentDate=" + paymentDate
               + "}";
    }

    // -----------------------------------------------------------------------------------------------------------------

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Staff getStaff() {
        return staff;
    }

    public void setStaff(final Staff staff) {
        this.staff = staff;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public Rental getRental() {
        return rental;
    }

    public void setRental(final Rental rental) {
        this.rental = rental;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    // -----------------------------------------------------------------------------------------------------------------
    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(final LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_CUSTOMER_ID, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_CUSTOMER)
    private Customer customer;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_STAFF_ID, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_STAFF)
    private Staff staff;

    @ManyToOne(optional = true)
    @JoinColumn(name = COLUMN_NAME_RENTAL_ID, nullable = true, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_RENTAL)
    private Rental rental;

    @Digits(integer = DIGITS_INTEGER_AMOUNT, fraction = DIGITS_FRACTION_AMOUNT)
    @DecimalMax((DECIMAL_MAX_AMOUNT))
    @DecimalMin(DECIMAL_MIN_AMOUNT)
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_AMOUNT, nullable = false, updatable = false, precision = DIGITS_INTEGER_AMOUNT,
            scale = DIGITS_FRACTION_AMOUNT)
    @NamedAttribute(ATTRIBUTE_NAME_AMOUNT)
    private BigDecimal amount;

    @PastOrPresent
    @NotNull
    @Basic(optional = false) //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = COLUMN_NAME_PAYMENT_DATE, nullable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_PAYMENT_DATE)
    private LocalDateTime paymentDate;
}
