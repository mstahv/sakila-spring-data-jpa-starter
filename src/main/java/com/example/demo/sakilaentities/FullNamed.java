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

import jakarta.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.Objects;

import static java.util.Comparator.comparing;

public interface FullNamed {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The attribute name for {@link FullNamedEntity#COLUMN_NAME_FIRST_NAME} column. The value is {@value}.
     */
    String ATTRIBUTE_NAME_FIRST_NAME = "firstName";

    /**
     * The minimum size for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute. The value is {@value}.
     */
    int SIZE_MIN_FIRST_NAME = 0; // TODO: 2019-07-14 empty???

    /**
     * The maximum size for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute. The value is {@value}.
     */
    int SIZE_MAX_FIRST_NAME = 45;

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The object attribute name for {@link FullNamedEntity#COLUMN_NAME_LAST_NAME} column. The value is {@value}.
     */
    String ATTRIBUTE_NAME_LAST_NAME = "lastName";

    /**
     * The minimum size for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute. The value is {@value}.
     */
    int SIZE_MIN_LAST_NAME = 0; // TODO: 2019-07-14 empty???

    /**
     * The maximum size for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute. The value is {@value}.
     */
    int SIZE_MAX_LAST_NAME = 45;

    // -----------------------------------------------------------------------------------------------------------------
    Comparator<FullNamed> COMPARING_FIRST_NAME = comparing(FullNamed::getFirstName);

    static Comparator<FullNamed> comparingFirstName(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_FIRST_NAME : COMPARING_FIRST_NAME.reversed();
    }

    Comparator<FullNamed> COMPARING_FIRST_NAME_IGNORE_CASE
            = (f1, f2) -> f1.getFirstName().compareToIgnoreCase(f2.getFirstName());

    static Comparator<FullNamed> comparingFirstNameIgnoreCase(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_FIRST_NAME_IGNORE_CASE : COMPARING_FIRST_NAME_IGNORE_CASE.reversed();
    }

    // -----------------------------------------------------------------------------------------------------------------
    Comparator<FullNamed> COMPARING_LAST_NAME = comparing(FullNamed::getLastName);

    static Comparator<FullNamed> comparingLastName(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_LAST_NAME : COMPARING_LAST_NAME.reversed();
    }

    Comparator<FullNamed> COMPARING_LAST_NAME_IGNORE_CASE
            = (f1, f2) -> f1.getLastName().compareToIgnoreCase(f2.getLastName());

    static Comparator<FullNamed> getComparingLastNameIgnoreCase(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_LAST_NAME_IGNORE_CASE : COMPARING_LAST_NAME_IGNORE_CASE.reversed();
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Indicates whether specified two objects have same values for both {@link #ATTRIBUTE_NAME_FIRST_NAME} attribute
     * and {@link #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @param o1 the first object to compare.
     * @param o2 the second object to compare.
     * @return {@code true} if two objects have same names; {@code false} otherwise.
     */
    static boolean equals(@NotNull final FullName o1, @NotNull final FullName o2) {
        if (o1 == null) {
            throw new NullPointerException("o1 is null");
        }
        if (o2 == null) {
            throw new NullPointerException("o2 is null");
        }
        return Objects.equals(o1.getFirstName(), o2.getFirstName())
               && Objects.equals(o1.getLastName(), o2.getLastName());
    }

    // ------------------------------------------------------------------------------------------------------- firstName

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    String getFirstName();

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute with specified value.
     *
     * @param firstName new value for {@value #ATTRIBUTE_NAME_FIRST_NAME} attribute.
     */
    void setFirstName(String firstName);

    // -------------------------------------------------------------------------------------------------------- lastName

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    String getLastName();

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_LAST_NAME} attribute with specified value.
     *
     * @param lastName new value for {@value #ATTRIBUTE_NAME_LAST_NAME} attribute.
     */
    void setLastName(String lastName);

    // -----------------------------------------------------------------------------------------------------------------
    default String getFullName(final FullNameFormatter formatter, final String delimiter) {
        return formatter.format(this, delimiter);
    }
}
