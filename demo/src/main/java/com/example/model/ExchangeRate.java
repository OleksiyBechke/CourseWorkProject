package com.example.model;

public class ExchangeRate {
    private Integer id;
    private Integer currencyId;
    private Integer dateId;
    private Integer exchangeRateUAH;

    public ExchangeRate(Integer id, Integer currencyId, Integer dateId, Integer exchangeRateUAH) {
        this.id = id;
        this.currencyId = currencyId;
        this.dateId = dateId;
        this.exchangeRateUAH = exchangeRateUAH;
    }

    public ExchangeRate(Integer currencyId, Integer dateId, Integer exchangeRateUAH) {
        this.currencyId = currencyId;
        this.dateId = dateId;
        this.exchangeRateUAH = exchangeRateUAH;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public Integer getExchangeRateUAH() {
        return exchangeRateUAH;
    }

    public void setExchangeRateUAH(Integer exchangeRateUAH) {
        this.exchangeRateUAH = exchangeRateUAH;
    }
}
