package com.example.dao;

import com.example.model.ExchangeRate;

import java.util.Collection;

public interface ExchangeRateDao {
    void create(ExchangeRate exchangeRate);
    ExchangeRate findById(Integer id);
    Collection<ExchangeRate> findAll();
    void update(ExchangeRate exchangeRate);
    void deleteByCurrencyId(Integer currencyId);
    void deleteById(Integer id);
}
