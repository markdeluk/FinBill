package com.marco.finbill.sql.exchange.exchange_api;

import android.icu.util.Currency;

import com.marco.finbill.sql.exchange.Exchange;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SearchForExchange {
    private Map<String, Double> exchangesFromCurrencyToCurrencies;

    public SearchForExchange(Currency fromCurrency) {
        exchangesFromCurrencyToCurrencies = null;
        ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
        Call<ExchangeResponse> call = exchangeApi.getExchange(fromCurrency.getCurrencyCode().toLowerCase());
        call.enqueue(new Callback<ExchangeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    exchangesFromCurrencyToCurrencies = response.body().getExchanges();
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {
            }
        });
    }

    public Map<String, Double> getExchangesFromCurrencyToCurrencies() {
        return exchangesFromCurrencyToCurrencies;
    }
}
