package com.marco.finbill.sql.exchange.exchange_api;

import java.util.HashMap;
import java.util.Map;


public class ExchangeResponse {
    public Map<String, Double> rates;

    public ExchangeResponse() {
        this.rates = new HashMap<>();
    }

    public Map<String, Double> getExchanges() {
        return rates;
    }
}
