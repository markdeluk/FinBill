package com.marco.finbill.sql.model.entity_fields;

public class ExchangeViewModelFields {

    private String fromCurrencyString;
    private String toCurrencyString;
    private Double exchangeRate;

    public ExchangeViewModelFields(String fromCurrencyString, String toCurrencyString, Double exchangeRate) {
        this.fromCurrencyString = fromCurrencyString;
        this.toCurrencyString = toCurrencyString;
        this.exchangeRate = exchangeRate;
    }

    public ExchangeViewModelFields() {
        this.fromCurrencyString = null;
        this.toCurrencyString = null;
        this.exchangeRate = null;
    }

    public String getFromCurrencyString() {
        return fromCurrencyString;
    }

    public void setFromCurrencyString(String fromCurrencyString) {
        this.fromCurrencyString = fromCurrencyString;
    }

    public String getToCurrencyString() {
        return toCurrencyString;
    }

    public void setToCurrencyString(String toCurrencyString) {
        this.toCurrencyString = toCurrencyString;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public boolean isValid() {
        return fromCurrencyString != null && toCurrencyString != null && exchangeRate != null;
    }

    public void clear() {
        this.fromCurrencyString = null;
        this.toCurrencyString = null;
        this.exchangeRate = null;
    }
}
