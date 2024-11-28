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

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Iterator;

import static com.example.demo.sakilaentities.BaseEntity.ATTRIBUTE_NAME_ID;
import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_FIRST_NAME;
import static com.example.demo.sakilaentities.FullNamedEntity.COLUMN_NAME_LAST_NAME;
import static com.example.demo.sakilaentities.Staff.COLUMN_NAME_STAFF_ID;
import static com.example.demo.sakilaentities.Staff.TABLE_NAME;
import static java.util.Optional.ofNullable;

@AttributeOverride(name = ATTRIBUTE_NAME_ID, column = @Column(name = COLUMN_NAME_STAFF_ID))
@Entity
@Table(name = TABLE_NAME)
public class Staff extends BaseEntity implements FullNamed {

    // -----------------------------------------------------------------------------------------------------------------
    public static final String TABLE_NAME = "staff";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_STAFF_ID = "staff_id";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ADDRESS_ID = "address_id";

    public static final String ATTRIBUTE_NAME_ADDRESS = "address";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_PICTURE = "picture";

    public static final String ATTRIBUTE_NAME_PICTURE = "picture";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_EMAIL = "email";

    public static final String ATTRIBUTE_NAME_EMAIL = "email";

    public static final int SIZE_MAX_EMAIL = 50;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_STORE_ID = "store_id";

    public static final String ATTRIBUTE_NAME_STORE = "store";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_ACTIVE = "active";

    public static final String ATTRIBUTE_NAME_ACTIVE = "active";

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_USERNAME = "username";

    public static final String ATTRIBUTE_NAME_USERNAME = "username";

    public static final int SIZE_MAX_USERNAME = 16;

    // -----------------------------------------------------------------------------------------------------------------
    public static final String COLUMN_NAME_PASSWORD = "password";

    public static final String ATTRIBUTE_NAME_PASSWORD = "password";

    public static final int SIZE_MAX_PASSWORD = 40;

    // -----------------------------------------------------------------------------------------------------------------

    @Override
    public String toString() {
        return super.toString() + "{"
               + "firstName=" + firstName
               + ",lastName=" + lastName
               + ",address=" + address
               + ",picture=" + Arrays.toString(picture)
               + ",email='" + email + '\''
               + ",store=" + store
               + ",active=" + active
               + ",username=" + username
               + ",password=" + password
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

    // --------------------------------------------------------------------------------------------------------- address
    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    // --------------------------------------------------------------------------------------------------------- picture
    public byte[] getPicture() {
        return ofNullable(this.picture).map(v -> Arrays.copyOf(v, v.length)).orElse(null);
    }

    public void setPicture(final byte[] picture) {
        this.picture = ofNullable(picture).map(v -> Arrays.copyOf(v, v.length)).orElse(null);
    }

    public String getPictureFormatName() throws IOException {
        final byte[] picture = getPicture();
        if (picture == null) {
            return null;
        }
        try (ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(picture))) {
            for (final Iterator<ImageReader> i = ImageIO.getImageReaders(iis); i.hasNext(); ) {
                try {
                    return i.next().getFormatName();
                } catch (final IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * Returns the current value of {@link #ATTRIBUTE_NAME_PICTURE} attribute as an image.
     *
     * @return the current value of {@link #ATTRIBUTE_NAME_PICTURE} attribute as an image; {@code null} if the value of
     * {@link #ATTRIBUTE_NAME_PICTURE} attribute is {@code null}.
     * @throws IOException if an I/O error occurs.
     * @see ImageIO#read(InputStream)
     */
    public BufferedImage getPictureASImage() throws IOException {
        final byte[] picture = getPicture();
        if (picture == null) {
            return null;
        }
        return ImageIO.read(new ByteArrayInputStream(picture));
    }

    /**
     * Replaces the current value of {@link #ATTRIBUTE_NAME_PICTURE} attribute with the value from specified image.
     *
     * @param renderedImage a image for {@link #ATTRIBUTE_NAME_PICTURE} attribute value.
     * @param formatName    an image format name.
     * @return the value of {@link ImageIO#write(RenderedImage, String, OutputStream)}.
     * @throws IOException if an I/O error occurs.
     */
    public boolean setPictureFromImage(@NotNull final RenderedImage renderedImage, @NotNull final String formatName)
            throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final boolean result = ImageIO.write(renderedImage, formatName, baos);
        setPicture(baos.toByteArray());
        return result;
    }

    // ----------------------------------------------------------------------------------------------------------- email
    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    // ----------------------------------------------------------------------------------------------------------- store
    public Store getStore() {
        return store;
    }

    public void setStore(final Store store) {
        this.store = store;
    }

    // ---------------------------------------------------------------------------------------------------------- active
    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    // -------------------------------------------------------------------------------------------------------- username
    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    // -------------------------------------------------------------------------------------------------------- password
    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
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

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_ADDRESS_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ADDRESS)
    private Address address;

    @Lob
    @Column(name = COLUMN_NAME_PICTURE)
    @NamedAttribute(ATTRIBUTE_NAME_PICTURE)
    private byte[] picture; // +

    @Size(max = SIZE_MAX_EMAIL)
    @Basic
    @Column(name = COLUMN_NAME_EMAIL)
    @NamedAttribute(ATTRIBUTE_NAME_EMAIL)
    private String email;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = COLUMN_NAME_STORE_ID, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_STORE)
    private Store store;

    @Basic(optional = false)
    @Column(name = COLUMN_NAME_ACTIVE, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_ACTIVE)
    private boolean active;

    @Size(max = SIZE_MAX_USERNAME)
    @NotNull
    @Basic(optional = false)
    @Column(name = COLUMN_NAME_USERNAME, nullable = false)
    @NamedAttribute(ATTRIBUTE_NAME_USERNAME)
    private String username;

    @Size(max = SIZE_MAX_PASSWORD)
    @Basic
    @Column(name = COLUMN_NAME_PASSWORD)
    @NamedAttribute(ATTRIBUTE_NAME_PASSWORD)
    private String password;
}
