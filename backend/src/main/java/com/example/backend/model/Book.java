package com.example.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "Book")
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date publishDate;

    private int pages;

    private int numeration;

    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    public Book() {}

    public Book(Date publishDate, int pages, int numeration, Collection collection) {
        this.publishDate = publishDate;
        this.pages = pages;
        this.numeration = numeration;
        this.collection = collection;
    }

    public Book(Date publishDate, int pages) {
        this.publishDate = publishDate;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getNumeration() {
        return numeration;
    }

    public void setNumeration(int numeration) {
        this.numeration = numeration;
    }

    public Collection getCollection() {return collection;}

    public void setCollection(Collection collection) {this.collection = collection;}
}
