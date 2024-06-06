package com.example.controller;

import com.example.dao.CurrencyDao;
import com.example.dao.DateDao;
import com.example.dao.ExchangeRateDao;
import com.example.model.Currency;
import com.example.model.Date;
import com.example.model.ExchangeRate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AdminController", value = {"/admin"})
public class AdminController extends HttpServlet {
    private CurrencyDao currencyDao;
    private DateDao dateDao;
    private ExchangeRateDao exchangeRateDao;

    @Override
    public void init() throws ServletException {
        super.init();
        currencyDao = (CurrencyDao) getServletContext().getAttribute("currencyDao");
        dateDao = (DateDao) getServletContext().getAttribute("dateDao");
        exchangeRateDao = (ExchangeRateDao) getServletContext().getAttribute("exchangeRateDao");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String command = request.getParameter("command");
        switch (command) {
            case "editCurrency":
                showEditCurrencyForm(request, response);
                break;
            case "redirectOnIndex":
                redirectOnIndex(request, response);
                break;
            case "redirectOnAdminPage":
                request.getRequestDispatcher("WEB-INF/jsp/adminPage.jsp").forward(request, response);
                break;
            case "redirectOnСurrenciesPage":
                redirectOnСurrenciesPage(request, response);
                break;
            case "redirectOnExchangeRatesPage":
                redirectOnExchangeRatesPage(request, response);
                break;
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String command = request.getParameter("command");
        if (command == null) {
            response.sendError(404, "Команду не знайдено");
            return;
        }

        switch (command) {
            case "createCurrency":
                createCurrency(request, response);
                break;
            case "saveCurrency":
                saveCurrency(request, response);
                break;
            case "deleteCurrency":
                deleteCurrency(request, response);
                break;
            case "createExchangeRate":
                createExchangeRate(request, response);
                break;
            default:
                response.sendError(404, "Некоректна команда");
                break;
        }
    }

    private void createCurrency(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            String currencyName = request.getParameter("currencyName");
            Currency currency = new Currency(currencyName);
            currencyDao.create(currency);
            redirectOnСurrenciesPage(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    private void createExchangeRate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Integer currencyId = Integer.valueOf(request.getParameter("currencyId"));
            Integer dateId = Integer.valueOf(request.getParameter("dateId"));
            Integer exchangeRateUAH = Integer.valueOf(request.getParameter("exchangeRateUAH"));
            ExchangeRate exchangeRate = new ExchangeRate(currencyId, dateId, exchangeRateUAH);
            exchangeRateDao.create(exchangeRate);
            redirectOnExchangeRatesPage(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    private void showEditCurrencyForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Integer currencyId = Integer.valueOf(request.getParameter("currencyId"));
            Currency currency = currencyDao.findById(currencyId);
            request.setAttribute("currency", currency);
            request.getRequestDispatcher("WEB-INF/jsp/editCurrency.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    private void redirectOnIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void saveCurrency(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Integer currencyId = Integer.valueOf(request.getParameter("currencyId"));
            String currencyName = request.getParameter("currencyName");

            Currency currency = new Currency(currencyId, currencyName);
            currencyDao.update(currency);
            redirectOnСurrenciesPage(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    private void deleteCurrency(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Integer currencyId = Integer.valueOf(request.getParameter("currencyId"));
            exchangeRateDao.deleteByCurrencyId(currencyId);
            currencyDao.deleteById(currencyId);
            redirectOnСurrenciesPage(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    private void redirectOnСurrenciesPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Collection<Currency> currencies = currencyDao.findAll();
        request.setAttribute("currencies", currencies);
        request.getRequestDispatcher("WEB-INF/jsp/currencies.jsp").forward(request, response);
    }

    private void redirectOnExchangeRatesPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("currencyDao", currencyDao);

        request.setAttribute("dateDao", dateDao);

        Collection<Currency> currencies = currencyDao.findAll();
        request.setAttribute("currencies", currencies);

        Collection<Date> dates = dateDao.findAll();
        request.setAttribute("dates", dates);

        Collection<ExchangeRate> exchangeRates = exchangeRateDao.findAll();
        request.setAttribute("exchangeRates", exchangeRates);

        request.getRequestDispatcher("WEB-INF/jsp/exchangeRates.jsp").forward(request, response);
    }
}
