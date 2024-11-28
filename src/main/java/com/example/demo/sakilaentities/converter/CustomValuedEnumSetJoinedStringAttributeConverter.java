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
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.regex.Pattern;

import static java.util.Collections.synchronizedMap;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

public abstract class CustomValuedEnumSetJoinedStringAttributeConverter<E extends Enum<E> & CustomValuedEnum<E, String>>
        implements AttributeConverter<Set<E>, String> {

    // -----------------------------------------------------------------------------------------------------------------
    private static final Map<String, Pattern> PATTERNS = synchronizedMap(new WeakHashMap<>());

    /**
     * Returns a pre-compiled pattern of specified regular expression.
     *
     * @param regex the regular expression to compile.
     * @return a compiled pattern of specified regular expression.
     */
    private static Pattern pattern(final String regex) {
        synchronized (PATTERNS) {
            return PATTERNS.computeIfAbsent(requireNonNull(regex, "regex is null"), Pattern::compile);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public CustomValuedEnumSetJoinedStringAttributeConverter(final Class<E> enumClass, final String columnDelimiter) {
        super();
        this.enumClass = requireNonNull(enumClass, "enumClass is null");
        this.columnDelimiter = requireNonNull(columnDelimiter, "columnDelimiter is null");
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    public String convertToDatabaseColumn(final Set<E> attribute) {
        return ofNullable(attribute)
                .map(a -> a.stream().map(E::getDatabaseColumn).collect(joining(columnDelimiter)))
                .orElse(null);
    }

    @Override
    public Set<E> convertToEntityAttribute(final String column) {
        return ofNullable(column)
                .map(j -> pattern(columnDelimiter).splitAsStream(j)
                        .map(s -> CustomValuedEnum.valueOfDatabaseColumn(enumClass, s))
                        .collect(toCollection(() -> EnumSet.noneOf(enumClass))))
                .orElse(null);
    }

    // -----------------------------------------------------------------------------------------------------------------
    final Class<E> enumClass;

    /**
     * The delimiter for splitting or joining the database column.
     */
    final String columnDelimiter;
}
