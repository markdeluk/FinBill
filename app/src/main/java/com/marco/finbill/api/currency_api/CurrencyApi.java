package com.marco.finbill.api.currency_api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyApi {
    @GET("latest/currencies.json")
    Call<CurrencyResponse> getCurrencies();
}
