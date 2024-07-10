package com.example.backend.dto;

public class BookPerMonthDTO {

    private String month;
    private Long count;

    public BookPerMonthDTO() {}

    public BookPerMonthDTO(String month, Long count) {
        this.month = month;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
