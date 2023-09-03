package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CollectionAuthorId implements Serializable {

    @Column(name = "collection_id")
    private Long collectionId;

    @Column(name = "author_id")
    private Long authorId;

    public CollectionAuthorId() {
    }

    public CollectionAuthorId(Long collectionId, Long authorId) {
        this.collectionId = collectionId;
        this.authorId = authorId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setBookId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }
}
