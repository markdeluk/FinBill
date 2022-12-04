package com.marco.finbill.api.currency_api;

import java.util.ArrayList;
import java.util.List;

public class CurrencyResponse {
    private List<String> currencies;

    public CurrencyResponse() {
        currencies = new ArrayList<>();
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public void putCurrency(String currency) {
        this.currencies.add(currency);
    }
}
