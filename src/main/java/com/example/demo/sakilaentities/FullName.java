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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_FIRST_NAME;
import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_LAST_NAME;
import static java.util.Comparator.comparing;

/**
 * An embeddable class for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute and {@value #ATTRIBUTE_NAME_LAST_NAME}
 * attribute.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Embeddable
public class FullName implements FullNamed, Serializable {

    // -----------------------------------------------------------------------------------------------------------------
    private static final long serialVersionUID = 672072114529715565L;

    // -----------------------------------------------------------------------------------------------------------------
    public static final Comparator</*? super */FullName> COMPARING_FIRST_NAME_NATURAL
            = comparing(FullName::getFirstName);

    public static final Comparator</*? super */FullName> COMPARING_FIRST_NAME_REVERSE
            = COMPARING_FIRST_NAME_NATURAL.reversed();

    // -----------------------------------------------------------------------------------------------------------------
    public static final Comparator</*? super */FullName> COMPARING_LAST_NAME_NATURAL
            = comparing(FullName::getLastName);

    public static final Comparator</*? super */FullName> COMPARING_LAST_NAME_REVERSE
            = COMPARING_LAST_NAME_NATURAL.reversed();

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance with specified first name and last name.
     *
     * @param firstName a value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     * @param lastName  a value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     * @return a new instance.
     */
    public static FullName of(final String firstName, final String lastName) {
        final FullName instance = new FullName();
        instance.setFirstName(firstName);
        instance.setLastName(lastName);
        return instance;
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
               + "firstName=" + firstName
               + ",lastName=" + lastName
               + "}";
    }

    /**
     * Indicates whether some other object is "equal to" this one..
     *
     * @param obj the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FullName)) {
            return false;
        }
        final FullName that = (FullName) obj;
        return Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }

    // ------------------------------------------------------------------------------------------------------- firstName

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with specified value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    @Override
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public FullName firstName(final String firstName) {
        setFirstName(firstName);
        return this;
    }

    // -------------------------------------------------------------------------------------------------------- lastName

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with specified value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    @Override
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public FullName lastName(final String lastName) {
        setLastName(lastName);
        return this;
    }

    // -----------------------------------------------------------------------------------------------------------------
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
}
