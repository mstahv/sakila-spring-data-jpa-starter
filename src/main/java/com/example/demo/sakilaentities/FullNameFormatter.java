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

/**
 * Constants for ordering names.
 */
public enum FullNameFormatter {

    /**
     * A constant for formatting a full name as the {@link FullNamed#ATTRIBUTE_NAME_FIRST_NAME} attribute first.
     */
    FIRST_NAME_FIRST() {
        @Override
        String format(final FullNamed object, final String delimiter) {
            return object.getFirstName() + delimiter + object.getLastName();
        }
    },

    /**
     * A constant for formatting a full name as the {@link FullName#ATTRIBUTE_NAME_LAST_NAME} attribute first.
     */
    LAST_NAME_FIRST() {
        @Override
        String format(final FullNamed object, final String delimiter) {
            return object.getLastName() + delimiter + object.getFirstName();
        }
    };

    // -------------------------------------------------------------------------------------------------------------

    /**
     * Formats specified full name using specified delimiter.
     *
     * @param object    the full name to format.
     * @param delimiter a delimiter between names.
     * @return a formatted value for specified full name.
     */
    abstract String format(final FullNamed object, final String delimiter);
}
