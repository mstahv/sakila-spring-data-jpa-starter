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

import jakarta.persistence.AttributeConverter;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;

public abstract class CustomValuedEnumAttributeConverter<E extends Enum<E> & CustomValuedEnum<E, T>, T>
        implements AttributeConverter<E, T> {

    // -----------------------------------------------------------------------------------------------------------------
    public CustomValuedEnumAttributeConverter(final Class<E> enumClass) {
        super();
        this.enumClass = requireNonNull(enumClass, "enumClass is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public T convertToDatabaseColumn(final E attribute) {
        return ofNullable(attribute).map(CustomValuedEnum::getDatabaseColumn).orElse(null);
    }

    @Override
    public E convertToEntityAttribute(final T column) {
        return ofNullable(column).map(c -> CustomValuedEnum.valueOfDatabaseColumn(enumClass, c)).orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<E> enumClass;
}
