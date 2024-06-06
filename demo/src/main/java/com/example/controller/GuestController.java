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
import java.util.TreeMap;

@WebServlet(name = "GuestController", value = {"/guest"})
public class GuestController extends HttpServlet {
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
        if (command == null) {
            command = "showAllExchangeRates";
        }

        String pageHeader = request.getParameter("header");
        if (pageHeader == null) {
            pageHeader = "Курси валют";
        }

        request.setAttribute("pageHeader", pageHeader);

        switch (command) {
            case "show_exchangeRates_allCurrencies_currentDay":
                showExchangeRatesOfAllCurrenciesForCurrentDay(request, response);
                break;

            case "show_exchangeRate_certainCurrency_periodOfTime":
                showExchangeRateOfCertainCurrencyForPeriodOfTime(request, response);
                break;

            case "redirectOnIndex":
                redirectOnIndex(request, response);
                break;

            case "showExchangeRates":
            default:
                showExchangeRates(request, response);
                break;
        }
    }

    private void redirectOnIndex(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void showExchangeRates(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("currencyDao", currencyDao);

        request.setAttribute("dateDao", dateDao);

        Collection<Currency> currencies = currencyDao.findAll();
        request.setAttribute("currencies", currencies);

        Collection<ExchangeRate> exchangeRates = exchangeRateDao.findAll();
        request.setAttribute("exchangeRates", exchangeRates);

        request.getRequestDispatcher("WEB-INF/jsp/guestPage.jsp").forward(request, response);
    }

    private void showExchangeRatesOfAllCurrenciesForCurrentDay(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Collection<Date> dates = dateDao.findAll();
        Collection<ExchangeRate> exchangeRates = exchangeRateDao.findAll();


        LocalDate currentDate = LocalDate.now();
        Integer currentDateId = -1;
        Collection<ExchangeRate> resultExchangeRates = new ArrayList<>();

        for (Date date : dates) {
            if (currentDate.equals(date.getLocalDate())) {
                currentDateId = date.getId();
            }
        }

        if (currentDateId >= 1) {
            for (ExchangeRate exchangeRate : exchangeRates) {
                if (exchangeRate.getDateId().equals(currentDateId)) {
                    resultExchangeRates.add(exchangeRate);
                }
            }
        }

        request.setAttribute("currencyDao", currencyDao);
        request.setAttribute("dateDao", dateDao);

        Collection<Currency> currencies = currencyDao.findAll();
        request.setAttribute("currencies", currencies);

        request.setAttribute("exchangeRates", resultExchangeRates);

        request.getRequestDispatcher("WEB-INF/jsp/guestPage.jsp").forward(request, response);
    }

    private void showExchangeRateOfCertainCurrencyForPeriodOfTime(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            LocalDate fromDate = LocalDate.parse(request.getParameter("fromDate"));
            LocalDate toDate = LocalDate.parse(request.getParameter("toDate"));
            Integer startDateId = -1;
            Integer endDateId = -1;
            Integer currencyId = Integer.valueOf(request.getParameter("currencyId"));
            if (currencyDao.findById(currencyId) == null) {
                response.sendError(400, "Некоректний ввід валюти");
            }

            Collection<Date> dates = dateDao.findAll();
            Collection<ExchangeRate> exchangeRates = exchangeRateDao.findAll();

            Collection<ExchangeRate> resultExchangeRates = new ArrayList<>();

            for (Date date : dates) {
                if (fromDate.equals(date.getLocalDate())) {
                    startDateId = date.getId();
                }
                if (toDate.equals(date.getLocalDate())) {
                    endDateId = date.getId();
                }
            }

            if (startDateId >= 1 && endDateId >= 1 && !toDate.isBefore(fromDate)) {
                for (ExchangeRate exchangeRate : exchangeRates) {
                    LocalDate dateOfExchangeRate = dateDao.findById(exchangeRate.getDateId()).getLocalDate();
                    if (exchangeRate.getCurrencyId().equals(currencyId) && !dateOfExchangeRate.isBefore(fromDate) && !dateOfExchangeRate.isAfter(toDate)) {
                        resultExchangeRates.add(exchangeRate);
                    }
                }
            } else {
                response.sendError(400, "Некоректний ввід дати");
            }

            request.setAttribute("fromDate", fromDate.toString());
            request.setAttribute("toDate", toDate.toString());
            request.setAttribute("currencyId", currencyId);

            request.setAttribute("currencyDao", currencyDao);
            request.setAttribute("dateDao", dateDao);

            Collection<Currency> currencies = currencyDao.findAll();
            request.setAttribute("currencies", currencies);

            request.setAttribute("exchangeRates", resultExchangeRates);

            request.getRequestDispatcher("WEB-INF/jsp/guestPage.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400, "Некоректний ввід");
        }
    }
}
