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
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import static com.example.demo.sakilaentities.Film.COLUMN_NAME_FILM_ID;
import static com.example.demo.sakilaentities.Film.TABLE_NAME;

@AttributeOverride(name = BaseEntity.ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_FILM_ID, nullable = false))
@Entity
@Table(name = TABLE_NAME)
public class FilmText {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "film_text";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_FILM_ID = "film_id";

    public static final String ATTRIBUTE_NAME_FILM_ID = "filmId";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_TITLE = "title";

    public static final String ATTRIBUTE_NAME_TITLE = "title";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_DESCRIPTION = "description";

    public static final String ATTRIBUTE_NAME_DESCRIPTION = "description";

    // -----------------------------------------------------------------------------------------------------------------
    @Id
    @Column(name = Film.COLUMN_NAME_FILM_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_FILM_ID)
    private int filmId;

    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_TITLE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_TITLE)
    private String title;

    @Basic
    @Column(name = COLUMN_NAME_DESCRIPTION)
    @NamedAttribute(ATTRIBUTE_NAME_DESCRIPTION)
    private String description;
}
