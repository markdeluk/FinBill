package com.marco.finbill.sql.exchange.exchange_api;

import android.icu.util.Currency;
import com.marco.finbill.sql.exchange.Exchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExchangeResponse {
    public Currency fromCurrency;
    public Map<Currency, Double> rates;

    public ExchangeResponse() {
        this.rates = new HashMap<>();
    }

    public List<Exchange> getExchanges() {
        List<Exchange> exchanges = new ArrayList<>();
        for (Map.Entry<Currency, Double> entry : rates.entrySet()) {
            Exchange exchange = new Exchange(fromCurrency, entry.getKey(), entry.getValue());
            exchanges.add(exchange);
        }
        return exchanges;
    }
}
