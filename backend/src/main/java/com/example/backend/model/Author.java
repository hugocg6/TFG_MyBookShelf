package com.example.backend.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity(name = "Author")
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NaturalId
    private String name;

    @OneToMany(mappedBy = "author")
    private List<CollectionAuthor> collections;

    public Author() {}

    public Author(String name, List<CollectionAuthor> collections) {
        this.name = name;
        this.collections = collections;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CollectionAuthor> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionAuthor> collections) {
        this.collections = collections;
    }
}
