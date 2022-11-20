package com.marco.finbill.sql.exchange.exchange_api;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ExchangeApi exchangeApi;

    public static ExchangeApi getExchangeApi() {
        if (exchangeApi == null) {
            GsonConverterFactory customGsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(ExchangeResponse.class, new ExchangeDeserializer()).create());
            exchangeApi = new Retrofit.Builder().baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/").addConverterFactory(customGsonConverterFactory).build().create(ExchangeApi.class);
        }
        return exchangeApi;
    }
}
