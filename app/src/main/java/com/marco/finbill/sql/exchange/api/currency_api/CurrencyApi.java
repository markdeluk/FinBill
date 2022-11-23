package com.marco.finbill.sql.exchange.api.currency_api;

import com.marco.finbill.sql.exchange.api.exchange_api.ExchangeResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyApi {
    @GET("latest/currencies.json")
    Call<CurrencyResponse> getCurrencies();
}
