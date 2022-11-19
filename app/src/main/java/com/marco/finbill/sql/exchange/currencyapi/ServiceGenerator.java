package com.marco.finbill.sql.exchange.currencyapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ExchangeApi exchangeApi;

    public static ExchangeApi getExchangeApi() {
        if (exchangeApi == null) {
            exchangeApi = new Retrofit.Builder()
                    .baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ExchangeApi.class);
        }
        return exchangeApi;
    }
}
