package com.example.dao;

import com.example.model.Date;

import java.util.Collection;
import java.util.TreeMap;

public class InMemoryDateDao implements DateDao {
    private TreeMap<Integer, Date> dates = new TreeMap<>();

    @Override
    public void create(Date date) {
        if(date.getId() == null) {
            int id = dates.isEmpty() ? 1 : dates.lastKey() + 1;
            date.setId(id);
            dates.put(id, date);
        }
        else {
            dates.put(date.getId(), date);
        }
    }

    @Override
    public Date findById(Integer id) {
        return dates.get(id);
    }

    @Override
    public Collection<Date> findAll() {
        return dates.values();
    }

    @Override
    public void update(Date date) {
        dates.put(date.getId(), date);
    }

    @Override
    public void deleteById(Integer id) {
        dates.remove(id);
    }
}
