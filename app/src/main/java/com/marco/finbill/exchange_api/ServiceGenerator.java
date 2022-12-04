package com.marco.finbill.exchange_api;

import com.google.gson.GsonBuilder;
import com.marco.finbill.api.currency_api.CurrencyApi;
import com.marco.finbill.api.currency_api.CurrencyResponse;
import com.marco.finbill.exchange_api.ExchangeApi;
import com.marco.finbill.exchange_api.ExchangeDeserializer;
import com.marco.finbill.exchange_api.ExchangeResponse;
import com.marco.finbill.api.currency_api.CurrencyDeserializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ExchangeApi exchangeApi;

    public static ExchangeApi getExchangeApi() {
        if (exchangeApi == null) {
            GsonConverterFactory customExchangeGsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(ExchangeResponse.class, new ExchangeDeserializer()).create());
            exchangeApi = new Retrofit.Builder().baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/").addConverterFactory(customExchangeGsonConverterFactory).build().create(ExchangeApi.class);
        }
        return exchangeApi;
    }

}
