package com.example.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "UserBook")
@Table(name = "user_book")
public class UserBook {

    @EmbeddedId
    private UserBookId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("bookId")
    private Book book;

    private Boolean read;

    private Date readingDate;

    private Long collectionId;

    public UserBook() {
    }

    public UserBook(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public UserBook(Book book, User user, UserBookId id, Long collectionId) {
        this.book = book;
        this.user = user;
        this.id = id;
        this.collectionId = collectionId;
        this.read = false;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public UserBookId getId() {
        return id;
    }

    public void setId(UserBookId id) {
        this.id = id;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Date getReadingDate() {
        return readingDate;
    }

    public void setReadingDate(Date readingDate) {
        this.readingDate = readingDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }
}
