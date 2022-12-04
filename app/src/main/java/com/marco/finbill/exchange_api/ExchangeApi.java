package com.marco.finbill.exchange_api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ExchangeApi {

    @GET("latest/currencies/{from}/{to}.json")
    Call<ExchangeResponse> getExchange(@Path("from") String from, @Path("to") String to);
}
