package com.example.demo.sakilaentities.converter;

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

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * An interface for enums of customized database column values.
 *
 * @param <E> enum type parameter
 * @param <T> database column type parameter
 */
public interface CustomValuedEnum<E extends Enum<E> & CustomValuedEnum<E, T>, T> {

    // -----------------------------------------------------------------------------------------------------------------
    static <E extends Enum<E> & CustomValuedEnum<E, T>, T> E valueOfDatabaseColumn(
            final Class<E> enumClass, final T databaseColumn) {
        for (E enumConstant : requireNonNull(enumClass, "enumClass is null").getEnumConstants()) {
            if (Objects.equals(enumConstant.getDatabaseColumn(), databaseColumn)) {
                return enumConstant;
            }
        }
        throw new IllegalArgumentException("no enum constant for database column: " + databaseColumn);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns a value for database column.
     *
     * @return a value for database column.
     */
    T getDatabaseColumn();
}
