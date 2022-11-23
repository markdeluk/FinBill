package com.marco.finbill.sql.exchange.api.exchange_api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExchangeApi {
    @GET("latest/currencies/{from}.json")
    Call<ExchangeResponse> getExchange(@Path("from") String from);
}
