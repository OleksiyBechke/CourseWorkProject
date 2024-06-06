package com.example.web;

import com.example.dao.*;
import com.example.util.TestDatabaseCreator;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class TestDatabaseServletListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        CurrencyDao currencyDao = new InMemoryCurrencyDao();
        TestDatabaseCreator.createTestDatabaseCreator(currencyDao);
        sce.getServletContext().setAttribute("currencyDao", currencyDao);

        DateDao dateDao = new InMemoryDateDao();
        TestDatabaseCreator.createTestDatabaseCreator(dateDao);
        sce.getServletContext().setAttribute("dateDao", dateDao);

        ExchangeRateDao exchangeRateDao = new InMemoryExchangeRateDao();
        TestDatabaseCreator.createTestDatabaseCreator(exchangeRateDao);
        sce.getServletContext().setAttribute("exchangeRateDao", exchangeRateDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
