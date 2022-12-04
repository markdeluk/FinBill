package com.marco.finbill.api;

import com.google.gson.GsonBuilder;
import com.marco.finbill.api.currency_api.CurrencyApi;
import com.marco.finbill.api.currency_api.CurrencyResponse;
import com.marco.finbill.api.exchange_api.ExchangeApi;
import com.marco.finbill.api.exchange_api.ExchangeDeserializer;
import com.marco.finbill.api.exchange_api.ExchangeResponse;
import com.marco.finbill.api.currency_api.CurrencyDeserializer;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ExchangeApi exchangeApi;
    private static CurrencyApi currencyApi;

    public static ExchangeApi getExchangeApi() {
        if (exchangeApi == null) {
            GsonConverterFactory customExchangeGsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(ExchangeResponse.class, new ExchangeDeserializer()).create());
            exchangeApi = new Retrofit.Builder().baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/").addConverterFactory(customExchangeGsonConverterFactory).build().create(ExchangeApi.class);
        }
        return exchangeApi;
    }

    public static CurrencyApi getCurrencyApi() {
        if (currencyApi == null) {
            GsonConverterFactory customCurrencyGsonConverterFactory = GsonConverterFactory.create(new GsonBuilder().registerTypeAdapter(CurrencyResponse.class, new CurrencyDeserializer()).create());
            currencyApi = new Retrofit.Builder().baseUrl("https://cdn.jsdelivr.net/gh/fawazahmed0/currency-api@1/").addConverterFactory(customCurrencyGsonConverterFactory).build().create(CurrencyApi.class);
        }
        return currencyApi;
    }
}
