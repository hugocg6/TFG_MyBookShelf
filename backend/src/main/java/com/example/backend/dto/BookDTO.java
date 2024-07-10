package com.example.backend.dto;

public class BookDTO {
    private long id;
    private int num;
    private String publishDate;
    private int pages;

    public BookDTO() {
    }

    public BookDTO(long id, int num, String publishDate, int pages) {
        this.id = id;
        this.num = num;
        this.publishDate = publishDate;
        this.pages = pages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
