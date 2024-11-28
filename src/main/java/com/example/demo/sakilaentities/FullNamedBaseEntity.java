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
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Comparator;

import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_FIRST_NAME;
import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_LAST_NAME;

@MappedSuperclass
abstract class FullNamedBaseEntity extends BaseEntity implements FullNamed {

    // -----------------------------------------------------------------------------------------------------------------
    static Comparator<FullNamedBaseEntity> COMPARING_FIRST_NAME = FullNamed.COMPARING_FIRST_NAME::compare;

    static Comparator<FullNamedBaseEntity> comparingFirstName(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_FIRST_NAME : COMPARING_FIRST_NAME.reversed();
    }

    static Comparator<FullNamedBaseEntity> COMPARING_FIRST_NAME_IGNORE_CASE
            = FullNamed.COMPARING_FIRST_NAME_IGNORE_CASE::compare;

    static Comparator<FullNamedBaseEntity> comparingFirstNameIgnoreCase(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_FIRST_NAME_IGNORE_CASE : COMPARING_FIRST_NAME_IGNORE_CASE.reversed();
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Comparator<FullNamedBaseEntity> COMPARING_LAST_NAME = FullNamed.COMPARING_LAST_NAME::compare;

    static Comparator<FullNamedBaseEntity> comparingLastName(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_LAST_NAME : COMPARING_LAST_NAME.reversed();
    }

    static Comparator<FullNamedBaseEntity> COMPARING_LAST_NAME_IGNORE_CASE
            = FullNamed.COMPARING_LAST_NAME_IGNORE_CASE::compare;

    static Comparator<FullNamedBaseEntity> getComparingLastNameIgnoreCase(final boolean naturalOrder) {
        return naturalOrder ? COMPARING_LAST_NAME_IGNORE_CASE : COMPARING_LAST_NAME_IGNORE_CASE.reversed();
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
