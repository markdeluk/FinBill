package com.marco.finbill.sql.exchange.api.currency_api;

import com.marco.finbill.sql.exchange.api.ServiceGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SearchForCurrency {
    private List<String> currencies;

    public SearchForCurrency() throws IOException {
        currencies = null;
        CurrencyApi currencyApi = ServiceGenerator.getCurrencyApi();
        Call<CurrencyResponse> call = currencyApi.getCurrencies();
        Response<CurrencyResponse> response = call.execute();
        if (response.isSuccessful()) {
            CurrencyResponse currencyResponse = response.body();
            if (currencyResponse != null) {
                currencies = currencyResponse.getCurrencies();
            }
        }
    }

    public List<String> getCurrencies() {
        return currencies;
    }
}
