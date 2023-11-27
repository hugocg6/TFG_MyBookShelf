package com.example.backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.Date;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date publishDate;

    private boolean read;

    private int pages;

    private int numeration;

    public Book() {}

    public Book(Date publishDate, boolean read, int pages, int numeration) {
        this.publishDate = publishDate;
        this.read = read;
        this.pages = pages;
        this.numeration = numeration;
    }

    public Book(Date publishDate, boolean read, int pages) {
        this.publishDate = publishDate;
        this.read = read;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
