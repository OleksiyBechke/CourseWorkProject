package com.example.dao;

import com.example.model.Date;

import java.util.Collection;

public interface DateDao {
    void create(Date date);
    Date findById(Integer id);
    Collection<Date> findAll();
    void update(Date date);
    void deleteById(Integer id);
}
