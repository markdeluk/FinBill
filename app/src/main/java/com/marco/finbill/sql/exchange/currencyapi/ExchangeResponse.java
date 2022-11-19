package com.marco.finbill.sql.exchange.currencyapi;

import android.icu.util.Currency;

import com.google.gson.annotations.SerializedName;
import com.marco.finbill.sql.exchange.Exchange;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;


public class ExchangeResponse {
    public Date date;
    public Currency fromCurrency;
    public Map<String, Double> rates;

    public ExchangeResponse() {
        this.rates = new HashMap<>();
    }

    public void add(String currency, Double rate) {
        this.rates.put(currency, rate);
    }

}
