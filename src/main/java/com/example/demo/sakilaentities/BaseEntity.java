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

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

import static java.util.Comparator.comparing;

/**
 * An abstract base class for entity classes.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@MappedSuperclass
public abstract class BaseEntity {

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The entity attribute name for {@code ..._id} columns. The value is {@value}.
     */
    public static final String ATTRIBUTE_NAME_ID = "id";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The database column name for {@link #ATTRIBUTE_NAME_LAST_UPDATE} attribute. The value is {@value}.
     */
    public static final String COLUMN_NAME_LAST_UPDATE = "last_update";

    /**
     * The entity attribute name for {@link #COLUMN_NAME_LAST_UPDATE} column.
     */
    public static final String ATTRIBUTE_NAME_LAST_UPDATE = "lastUpdate";

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * A comparator for comparing entities with {@link #ATTRIBUTE_NAME_ID} attribute.
     */
    public static final Comparator<BaseEntity> COMPARING_ID = comparing(BaseEntity::getId);

    public static Comparator<BaseEntity> comparingId(final boolean natural) {
        return natural ? COMPARING_ID : COMPARING_ID.reversed();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if(this.id == null) {
            return false;
        }

        if (obj instanceof BaseEntity && obj.getClass().equals(getClass())) {
            return this.id.equals(((BaseEntity) obj).id);
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    // -------------------------------------------------------------------------------------------------------------- id

    /**
     * Returns the current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     *
     * @return the current value of {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Replaces the current value of {@value #ATTRIBUTE_NAME_ID} attribute specified value.
     *
     * @param id new value for {@value #ATTRIBUTE_NAME_ID} attribute.
     */
    void setId(final Integer id) {
        this.id = id;
    }

    // ------------------------------------------------------------------------------------------------------ lastUpdate

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_LAST_UPDATE} attribute.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_LAST_UPDATE} attribute.
     */
    public Date getLastUpdate() {
        // TODO: 2019-07-12 copy!!!
        return lastUpdate;
    }

    void setLastUpdate(final Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // ..._id ...INT PK NN UN AI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NamedAttribute(ATTRIBUTE_NAME_ID)
    private Integer id;

    // last_update TIMESTAMP NN CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    // !Payment !Customer
    //@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = COLUMN_NAME_LAST_UPDATE, nullable = /* ??? */ false, insertable = false, updatable = false)
    @NamedAttribute(ATTRIBUTE_NAME_LAST_UPDATE)
    private Date lastUpdate;
}
