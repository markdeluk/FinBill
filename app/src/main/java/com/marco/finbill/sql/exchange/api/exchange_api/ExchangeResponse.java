package com.marco.finbill.sql.exchange.api.exchange_api;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExchangeResponse {
    private LinkedHashMap<String, Double> rates;

    public ExchangeResponse() {
        rates = new LinkedHashMap<>();
    }

    public LinkedHashMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(LinkedHashMap<String, Double> rates) {
        this.rates = rates;
    }

    public void putRate(String key, Double value) {
        rates.put(key, value);
    }
}
