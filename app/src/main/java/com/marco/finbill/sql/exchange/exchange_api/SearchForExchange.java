package com.marco.finbill.sql.exchange.exchange_api;

import android.icu.util.Currency;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class SearchForExchange {

    private MutableLiveData<Map<String, Double>> rates;

    public SearchForExchange() {
        this.rates = new MutableLiveData<>();
    }

    private void searchForExchange(Currency fromCurrency) {
        ExchangeApi exchangeApi = ServiceGenerator.getExchangeApi();
        Call<ExchangeResponse> call = exchangeApi.getExchange(fromCurrency.getCurrencyCode().toLowerCase());
        call.enqueue(new Callback<ExchangeResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ExchangeResponse> call, Response<ExchangeResponse> response) {
                if (response.isSuccessful()) {
                    ExchangeResponse exchangeResponse = response.body();
                    if (exchangeResponse != null) {
                        rates.setValue(exchangeResponse.getRates());
                        Log.e("ExchangeUpdateCurrency", "onResponse: " + rates.getValue());
                    }
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ExchangeResponse> call, Throwable t) {
            }
        });
    }

}
