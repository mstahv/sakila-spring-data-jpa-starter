package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * TODO figure out if this should be removed altogether, old obsoleted mysql hack. So not really
 * needed for MySQL version either...
 * Quote from https://dev.mysql.com/doc/sakila/en/sakila-structure-tables-film-text.html:
 * 5.1.10 The film_text Table
 *
 * The film_text table contains the film_id, title and description columns of the film table, with the contents of the table kept in synchrony with the film table by means of triggers on film table INSERT, UPDATE and DELETE operations (see Section 5.5, “Triggers”).
 *
 * Before MySQL server 5.6.10, the film_text table was the only table in the Sakila sample database that used the MyISAM storage engine. This is because full-text search is used for titles and descriptions of films listed in the film table. MyISAM was used because full-text search support with InnoDB was not available until MySQL server 5.6.10.
 */
//@Entity
@Table(name = "film_text")
public class FilmText {
    @Id
    @Column(name = "film_id", nullable = false)
    private Short id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}