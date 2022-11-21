package com.marco.finbill.sql.exchange.exchange_api;

import java.util.HashMap;
import java.util.Map;


public class ExchangeResponse {
    private Map<String, Double> rates;

    public ExchangeResponse() {
        this.rates = new HashMap<>();
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public void putRates(String key, Double value) {
        this.rates.put(key, value);
    }
}
