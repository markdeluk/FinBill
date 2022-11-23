package com.marco.finbill.sql.exchange.api.exchange_api;
import android.icu.util.Currency;

import com.marco.finbill.sql.exchange.api.ServiceGenerator;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;

public class SearchForExchange {
    private Map<String, Double> rates;

    public SearchForExchange(Currency fromCurrency) throws IOException {
        rates = null;
        ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
        Call<ExchangeResponse> call = exchangeApi.getExchange(fromCurrency.getCurrencyCode().toLowerCase());
        Response<ExchangeResponse> response = call.execute();
        if (response.isSuccessful()) {
            ExchangeResponse exchangeResponse = response.body();
            if (exchangeResponse != null) {
                rates = exchangeResponse.getRates();
            }
        }
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}