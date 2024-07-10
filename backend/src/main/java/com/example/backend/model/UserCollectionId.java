package com.example.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserCollectionId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "collection_id")
    private Long collectionId;

    public UserCollectionId() {
    }

    public UserCollectionId(Long userId, Long collectionId) {
        this.userId = userId;
        this.collectionId = collectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }
}
