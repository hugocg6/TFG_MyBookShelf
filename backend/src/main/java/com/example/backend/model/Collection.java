package com.example.backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.sql.Blob;
import java.util.List;

@Entity(name = "Collection")
@Table(name = "collection")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String name;

    @OneToMany(mappedBy = "collection")
    private List<Book> books;

    @OneToMany(mappedBy = "collection")
    private List<CollectionAuthor> author;

    @ManyToOne
    private Publisher publisher;

    @Column(length = 1000)
    private String plot;

    @OneToMany(mappedBy = "collection")
    private List<UserCollection> users;

    @ManyToOne
    private Demography demography;

    @Lob
    private Blob bookImage;

    public Collection() {}

    public Collection(String name, List<Book> books, List<CollectionAuthor> author, Publisher publisher, String plot, Demography demography, Blob bookImage) {
        this.name = name;
        this.books = books;
        this.author = author;
        this.publisher = publisher;
        this.plot = plot;
        this.demography = demography;
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

    public List<CollectionAuthor> getAuthor() {
        return author;
    }

    public void setAuthor(List<CollectionAuthor> author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public List<UserCollection> getUsers() {
        return users;
    }

    public void setUsers(List<UserCollection> users) {
        this.users = users;
    }

    public Demography getDemography() {
        return demography;
    }

    public void setDemography(Demography demography) {
        this.demography = demography;
    }
}
