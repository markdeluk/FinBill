package com.marco.finbill.sql.exchange.exchange_api;
import android.icu.util.Currency;
import android.util.Log;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SearchForExchange {
    private Map<String, Double> rates;
    private boolean finished;

    public SearchForExchange(Currency fromCurrency) {
        finished = false;
        rates = null;
        ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
        Call<ExchangeResponse> call = exchangeApi.getExchange(fromCurrency.getCurrencyCode().toLowerCase());
        call.enqueue(new Callback<ExchangeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    rates = response.body().getRates();
                    finished = true;
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {
            }
        });
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public boolean isFinished() {
        return finished;
    }
}