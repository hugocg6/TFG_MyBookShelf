package com.example.backend.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.util.List;

@Entity(name = "User")
@Table(name="user_table")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String mail;
    private String name;

    private boolean admin;

    @Lob
    private Blob profileImg;

    @OneToMany (mappedBy = "user")
    private List<UserCollection> collections;

    public User() {}

    public User(String mail, String name, boolean admin, Blob profileImg, List<UserCollection> collections) {
        this.mail = mail;
        this.name = name;
        this.admin = admin;
        this.profileImg = profileImg;
        this.collections = collections;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Blob getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(Blob profileImg) {
        this.profileImg = profileImg;
    }

    public List<UserCollection> getCollections() {
        return collections;
    }

    public void setCollections(List<UserCollection> collections) {
        this.collections = collections;
    }
}
