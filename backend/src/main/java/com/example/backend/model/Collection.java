package com.example.backend.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.List;

@Entity
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany
    private List<Book> books;

    @Lob
    private Blob bookImage;

    public Collection() {}

    public Collection(Long id, String name, List<Book> books, Blob bookImage) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.bookImage = bookImage;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Blob getBookImage() {
        return bookImage;
    }

    public void setBookImage(Blob bookImage) {
        this.bookImage = bookImage;
    }
}
