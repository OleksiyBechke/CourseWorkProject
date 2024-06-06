package com.example.dao;

import com.example.model.ExchangeRate;

import java.util.*;

public class InMemoryExchangeRateDao implements ExchangeRateDao {
    private TreeMap<Integer, ExchangeRate> exchangeRates = new TreeMap<>();

    @Override
    public void create(ExchangeRate exchangeRate) {
        if(exchangeRate.getId() == null) {
            int id = exchangeRates.isEmpty() ? 1 : exchangeRates.lastKey() + 1;
            exchangeRate.setId(id);
            exchangeRates.put(id, exchangeRate);
        }
        else {
            exchangeRates.put(exchangeRate.getId(), exchangeRate);
        }
    }

    @Override
    public ExchangeRate findById(Integer id) {
        return exchangeRates.get(id);
    }

    @Override
    public Collection<ExchangeRate> findAll() {
        return exchangeRates.values();
    }

    @Override
    public void update(ExchangeRate exchangeRate) {
        exchangeRates.put(exchangeRate.getId(), exchangeRate);
    }

    @Override
    public void deleteByCurrencyId(Integer currencyId){
        Iterator<Map.Entry<Integer, ExchangeRate>> iterator = exchangeRates.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, ExchangeRate> entry = iterator.next();
            ExchangeRate exchangeRate = entry.getValue();
            if (exchangeRate.getCurrencyId().equals(currencyId)) {
                iterator.remove();
            }
        }
    }

    @Override
    public void deleteById(Integer id) {
        exchangeRates.remove(id);
    }
}
