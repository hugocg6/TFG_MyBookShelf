package com.example.backend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity(name = "UserCollection")
@Table(name = "user_collection")
public class UserCollection {

    @EmbeddedId
    private UserCollectionId id;

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("collectionId")
    private Collection collection;

    private boolean read;

    private Date readDate;

    private boolean added;

    public UserCollection() {
    }

    public UserCollection(User user, Collection collection) {
        this.id = new UserCollectionId(user.getId(), collection.getId());
        this.user = user;
        this.collection = collection;
    }

    public UserCollectionId getId() {
        return id;
    }

    public void setId(UserCollectionId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }

    public Date getReadDate() {
        return readDate;
    }

    public void setReadDate(Date readDate) {
        this.readDate = readDate;
    }
}
