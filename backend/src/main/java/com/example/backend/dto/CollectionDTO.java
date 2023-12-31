package com.example.backend.dto;

import java.sql.Blob;
import java.util.List;

public class CollectionDTO {

    private long id;
    private String name;
    private List<BookDTO> books;
    private List<String> author;
    private String publisher;
    private String plot;

    private String demography;

    public CollectionDTO() {
    }

    public CollectionDTO(long id, String name, List<BookDTO> books, List<String> author, String publisher, String plot, String demography) {
        this.id = id;
        this.name = name;
        this.books = books;
        this.author = author;
        this.publisher = publisher;
        this.plot = plot;
        this.demography = demography;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getDemography() {
        return demography;
    }

    public void setDemography(String demography) {
        this.demography = demography;
    }
}
