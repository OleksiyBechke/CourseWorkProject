package com.example.model;

import java.time.LocalDate;

public class Date {
    private Integer id;
    private LocalDate localDate;

    public Date(Integer id, LocalDate localDate) {
        this.id = id;
        this.localDate = localDate;
    }

    public Date(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
