package com.marco.finbill.sql.exchange.currencyapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExchangeApi {
    @GET("1/latest/currencies/{from}")
    Call<ExchangeResponse> getExchange(@Path("from") String from);
}
