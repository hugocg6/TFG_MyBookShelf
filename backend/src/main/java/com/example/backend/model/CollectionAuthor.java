package com.example.backend.model;

import jakarta.persistence.*;

@Entity(name = "CollectionAuthor")
@Table(name = "collection_author")
public class CollectionAuthor {

    @EmbeddedId
    private CollectionAuthorId id;

    @ManyToOne
    @MapsId("collectionId")
    private Collection collection;

    @ManyToOne
    @MapsId("authorId")
    private Author author;

    public CollectionAuthor() {
    }

    public CollectionAuthor(CollectionAuthorId id, Collection collection, Author author) {
        this.id = id;
        this.collection = collection;
        this.author = author;
    }

    public CollectionAuthorId getId() {
        return id;
    }

    public void setId(CollectionAuthorId id) {
        this.id = id;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
