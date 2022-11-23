package com.marco.finbill.sql.exchange.api.exchange_api;

import java.util.HashMap;
import java.util.Map;

public class ExchangeResponse {
    private Map<String, Double> rates;

    public ExchangeResponse() {
        rates = new HashMap<>();
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public void putRate(String key, Double value) {
        rates.put(key, value);
    }
}
