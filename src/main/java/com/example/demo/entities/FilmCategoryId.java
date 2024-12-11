package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.util.Objects;

@Embeddable
public class FilmCategoryId implements java.io.Serializable {
    private static final long serialVersionUID = -7279561690929283487L;
    @Column(name = "film_id", columnDefinition = "smallint UNSIGNED not null")
    private Integer filmId;

    @Column(name = "category_id", columnDefinition = "tinyint UNSIGNED not null")
    private Short categoryId;

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Short categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FilmCategoryId entity = (FilmCategoryId) o;
        return Objects.equals(this.filmId, entity.filmId) &&
                Objects.equals(this.categoryId, entity.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, categoryId);
    }

    @Override
    public String toString() {
        return "FilmCategoryId{" +
                "filmId=" + filmId +
                ", categoryId=" + categoryId +
                '}';
    }
}