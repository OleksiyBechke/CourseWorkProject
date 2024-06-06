package com.example.util;

import com.example.dao.CurrencyDao;
import com.example.dao.DateDao;
import com.example.dao.ExchangeRateDao;
import com.example.model.Currency;
import com.example.model.Date;
import com.example.model.ExchangeRate;

import java.time.LocalDate;
import java.util.Random;

public class TestDatabaseCreator {
    public static void createTestDatabaseCreator(CurrencyDao currencyDao) {
        currencyDao.create(new Currency(1,"Долар"));
        currencyDao.create(new Currency(2,"Євро"));
        currencyDao.create(new Currency(3,"Фунт"));
    }

    public static void createTestDatabaseCreator(DateDao dateDao) {
        int id = 0;
        for (int i = -7; i <= 7; i++) {
            id++;
            dateDao.create(new Date(id, LocalDate.now().plusDays(i)));
        }
    }

    public static void createTestDatabaseCreator(ExchangeRateDao exchangeRateDao) {
        Random random = new Random();
        int id = 0;
        for (int i = 1; i <= 15; i++) {
            id++;
            exchangeRateDao.create(new ExchangeRate(1, id, random.nextInt(101)));
            exchangeRateDao.create(new ExchangeRate(2, id, random.nextInt(101)));
            exchangeRateDao.create(new ExchangeRate(3, id, random.nextInt(101)));
        }
    }
}
