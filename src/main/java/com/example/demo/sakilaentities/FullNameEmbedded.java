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

import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * An interface for entities to which an {@link FullName} is embedded.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public interface FullNameEmbedded {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The name of the attribute of {@link FullName}. The value is {@value}.
     */
    String ATTRIBUTE_NAME_FULL_NAME = "fullName";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_FULL_NAME} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    FullName getFullName();

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_FULL_NAME} attribute with specified value.
     *
     * @param fullName new value for  {@link #ATTRIBUTE_NAME_FULL_NAME} attribute.
     */
    void setFullName(FullName fullName);

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Returns the current value of {@link FullName#ATTRIBUTE_NAME_FIRST_NAME} attribute of {@link
     * #ATTRIBUTE_NAME_FULL_NAME} attribute.
     *
     * @return an optional of {@link FullName#ATTRIBUTE_NAME_FIRST_NAME} attribute of {@link #ATTRIBUTE_NAME_FULL_NAME}
     * attribute; {@code null} if either {@link #ATTRIBUTE_NAME_FULL_NAME} attribute or {@code null} or {@link
     * FullName#ATTRIBUTE_NAME_FIRST_NAME} attribute is {@code null}.
     */
    default Optional<String> getFirstName() {
        return ofNullable(getFullName()).map(FullName::getFirstName);
    }

    // TODO: 2019-07-15 remove!!! state changes regardless of parameter!!!
    @Deprecated // forRemoval = true
    default void setFirstName(final String firstName) {
        ofNullable(getFullName())
                .orElseGet(() -> {
                    setFullName(new FullName()); // TODO: 2019-07-15 not good!!!
                    return getFullName();
                })
                .setFirstName(firstName);
    }

    // -----------------------------------------------------------------------------------------------------------------
    default Optional<String> getLastName() {
        return ofNullable(getFullName()).map(FullName::getLastName);
    }

    // TODO: 2019-07-15 remove!!! state changes regardless of parameter!!!
    @Deprecated // forRemoval = true
    default void setLastName(final String lastName) {
        ofNullable(getFullName())
                .orElseGet(() -> {
                    setFullName(new FullName()); // TODO: 2019-07-15 not good!!!
                    return getFullName();
                })
                .setLastName(lastName);
    }
}
