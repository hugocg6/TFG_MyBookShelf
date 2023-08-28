package com.example.backend.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToMany
    private List<Author> author;

    @OneToOne
    private Publisher publisher;

    private int cover;

    private String plot;

    public Book() {}

    public Book(Long id, String title, List<Author> author, Publisher publisher, int cover, String plot) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.cover = cover;
        this.plot = plot;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}
